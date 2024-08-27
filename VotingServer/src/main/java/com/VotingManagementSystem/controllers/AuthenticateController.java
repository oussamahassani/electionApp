package com.VotingManagementSystem.controllers;

import com.VotingManagementSystem.DTO.SendEmail;
import com.VotingManagementSystem.config.JwtUtils;
import com.VotingManagementSystem.exceptions.UserNotPermittedException;
import com.VotingManagementSystem.models.JwtRequest;
import com.VotingManagementSystem.models.JwtResponse;
import com.VotingManagementSystem.models.UserRole;
import com.VotingManagementSystem.models.VoterVerification;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.repositories.RoleRepository;
import com.VotingManagementSystem.repositories.UserRoleRepository;
import com.VotingManagementSystem.repositories.VoterVerificationRepository;
import com.VotingManagementSystem.services.ElectionService;
import com.VotingManagementSystem.services.UserService;
import com.VotingManagementSystem.services.Impl.UserDetailsImpl;
import com.VotingManagementSystem.services.Impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import com.VotingManagementSystem.models.User;
@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ElectionService electionService;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VoterVerificationRepository verificationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRole;


    @Autowired
    private UserService userService;
    
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw  new Exception("User not found");
        }

        //Authenticate
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        
        String token = this.jwtUtils.generateToken(userDetails);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        //get all entry of userRoles
       Set<UserRole> ur = new HashSet<>(userRole.findAll());

        //get all entry of VoterValidation
        List<String> roles = authorities.stream()
        	    .map(GrantedAuthority::getAuthority)
        	    .collect(Collectors.toList());
        
     // for (UserRole u: ur){
            // check if userRole is admin or not. If Admin then pass token to the user.
            if(Objects.equals(roles.get(0), "ADMIN")){
                if (Objects.equals(userDetails.getUsername(), jwtRequest.getUsername())){
                    return  ResponseEntity.ok(new JwtResponse(token));
                }
            }else if(Objects.equals(roles.get(0), "NORMAL")){
            	User userDetail = userService.findUserByEmail(userDetails.getUsername());
            	  VoterVerification verifications = verificationRepository.findByIdUser(userDetail.getUserId());
                //if userRole is Normal then check in the validation table if user is verified or not
                
                    if (verifications.getStatus()==null){
                        throw new UserNotPermittedException("User is not verified");
                    }
               
            }
      //}

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception{
         try{
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

         }catch (DisabledException e){
             throw new Exception("USER DISABLED "+e.getMessage());

         }catch (BadCredentialsException e){
             throw new BadCredentialsException(e.getMessage());
         }
    }

    //returns the details of current user
    @GetMapping("/current-user")
    public Map<String, Object> getCurrentUser(Principal principal){
        UserDetailsImpl userDetails = (UserDetailsImpl) this.userDetailsService.loadUserByUsername(principal.getName());

        //set password to empty so the user can see it
        userDetails.getUser().setPassword("");

        Map<String, Object> response = new HashMap<>();
        response.put("user", userDetails.getUser());
        response.put("authorities", userDetails.getAuthorities());

        return response;
    }

    @PostMapping("/countctUs")
    public void countctUs(@RequestBody  SendEmail email){
        userService.countctUs(email);
    }
    @PutMapping("/elction/update")
    public ResponseEntity<?> updateElection(@RequestBody Election election){
        return ResponseEntity.ok(electionService.updateElection(election));
    }
}
