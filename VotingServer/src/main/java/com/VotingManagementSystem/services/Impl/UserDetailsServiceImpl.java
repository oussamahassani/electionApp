package com.VotingManagementSystem.services.Impl;

import com.VotingManagementSystem.exceptions.UserNotFoundException;
import com.VotingManagementSystem.exceptions.UserNotPermittedException;
import com.VotingManagementSystem.models.Role;
import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.UserRole;
import com.VotingManagementSystem.models.VoterVerification;
import com.VotingManagementSystem.repositories.RoleRepository;
import com.VotingManagementSystem.repositories.UserRepository;
import com.VotingManagementSystem.repositories.UserRoleRepository;
import com.VotingManagementSystem.repositories.VoterVerificationRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);

        if (user == null) {
            throw new UserNotFoundException("user not found !!");
        }else{
            return new UserDetailsImpl(user);
        }
    }
}
