package com.VotingManagementSystem.services.Impl;

import com.VotingManagementSystem.DTO.SendEmail;
import com.VotingManagementSystem.exceptions.UserAlreadyExitsException;
import com.VotingManagementSystem.exceptions.UserNotFoundException;
import com.VotingManagementSystem.models.Role;
import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.UserRole;
import com.VotingManagementSystem.models.VoterVerification;
import com.VotingManagementSystem.repositories.RoleRepository;
import com.VotingManagementSystem.repositories.UserRepository;
import com.VotingManagementSystem.repositories.UserRoleRepository;
import com.VotingManagementSystem.services.MailSendService;
import com.VotingManagementSystem.services.UserService;
import com.VotingManagementSystem.services.VoterVerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private VoterVerificationService verificationService;

    @Autowired
    private MailSendService mailSendService;

    @Override
    public User addAdminUser(User user) {
        User local = userRepository.findByEmail(user.getEmail());
        if(local!=null){
            throw new UserAlreadyExitsException("User with provide mail ID : "+local.getEmail()+ " already Exists !!");
        }

        Role role1 = roleRepository.findByName("ADMIN");

        //saving user and role in userRole
        UserRole userRole= new UserRole();
        userRole.setRole(role1);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);
        return userRepository.save(user);
    }

    @Override
    public User addUser(User user) {

        User local = userRepository.findByEmail(user.getEmail());
        if(local!=null){
            throw new UserAlreadyExitsException("User with provide mail ID : "+user.getEmail()+ " already exists !!");
        }
        Role role1 = roleRepository.findByName("NORMAL");

        //saving user and role in userRole
        UserRole userRole= new UserRole();
        userRole.setRole(role1);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);
        User data = userRepository.save(user);

        //adding user id to verification list
        VoterVerification verification = new VoterVerification();
        verification.setUser(data);
        System.out.println(verification);
        verificationService.addVerificationDetails(verification);

        return data;
    }

    @Override
    public User updateUser(User user){
       return userRepository.save(user);
    }

    @Override
    public User getUser(String email) {

        User local = userRepository.findByEmail(email);
        if(local==null){
            throw new UserNotFoundException("User with provide mail ID : "+email+ " does not exists !!");
        }
        local.setPassword("");
        return local; //need to throw exception
    }

    @Override
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user!=null){
            return user;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>(userRepository.findAll());
        list.forEach(user -> user.setPassword(""));
        list.removeIf(a -> Objects.equals(a.getEmail(), "admin@gmail.com"));

        return list;
    }

    @Override
    public void deleteUser(String email) {
        User local = userRepository.findByEmail(email);
        if(local==null){
            throw new UserNotFoundException("User with provide mail ID : "+email+ " does not exists !!");
        }

        Set<UserRole> userRoles = local.getUserRoles();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
              //  userRole.setRole(null);
               // userRole.setUser(null);
                local.getUserRoles().remove(userRole);
                userRoleRepository.deleteById(userRole.getUserRoleId());
            }
        }

        userRepository.deleteById(local.getUserId());
    }

	@Override
	public void countctUs(SendEmail email) {
		// TODO Auto-generated method stub
	    mailSendService.testemail("admin@gmail.com", email.getMessage() + "le mesage envoyer de la part de " + email.getName() + " Numero de telephone est "+  email.getPhone() , "nouvaux email form contect Us"
	    		);
	}

	@Override
	public List<Object[]> countUserByDate() {
		// TODO Auto-generated method stub
		return userRepository.countUserByDate();
	}

	@Override
	public List<User> findlastThreeUser() {
		// TODO Auto-generated method stub
		return userRepository.findlastThreeUser();
	}

	@Override
	public Long counUser() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public User findUserByEmail(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(username);
	}

	@Override
	public User addUserByAdmin(User user) {
		User local = userRepository.findByEmailAndCin(user.getEmail(),user.getCin());
        if(local!=null){
            throw new UserAlreadyExitsException("User with provide mail ID : "+user.getEmail()+ " already exists !!");
        }
        Role role1 = roleRepository.findByName("NORMAL");

        //saving user and role in userRole
        UserRole userRole= new UserRole();
        userRole.setRole(role1);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);
        User data = userRepository.save(user);

        //adding user id to verification list
        VoterVerification verification = new VoterVerification();
        verification.setUser(data);
        verification.setStatus("Approved");
        System.out.println(verification);
        verificationService.addVerificationDetails(verification);
		return user;
	}

	@Override
	public User findUserByCin(String cin , String password) {
		// TODO Auto-generated method stub
	User user = 	userRepository.findUserByCin(cin);
	    mailSendService.testemail(user.getEmail(),  "votre nouveaux mot de passe est " + password +  " votre login est"+user.getEmail()  , "nouvaux password");

		return user;
	}


}
