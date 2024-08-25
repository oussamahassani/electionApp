package com.VotingManagementSystem.controllers;

import com.VotingManagementSystem.exceptions.PasswordDoesNotMatchException;
import com.VotingManagementSystem.models.ChangePassword;
import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.VoterVerification;
import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.services.CandidateService;
import com.VotingManagementSystem.services.ElectionService;
import com.VotingManagementSystem.services.UserService;
import com.VotingManagementSystem.services.VoterVerificationService;
import com.VotingManagementSystem.services.VotesCounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VoterVerificationService verificationService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ElectionService electionService;
    
    @Autowired
    private VotesCounterService votesCounterService;
    
    @Autowired
    private CandidateService candidateService;
    
    @PostMapping("/admin")
    public ResponseEntity<User> addAdmin(@RequestBody User user){

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.addAdminUser(user));
    }

//    private static final String UPLOADED_FOLDER = ".//..//..//VotingManagementSystem//VotingServer//src//main//resources//static//images//";
//    private static final String DEFAULT_IMAGE = UPLOADED_FOLDER + "photo_1698335878080";

//    @PostMapping("/voter")
//    public ResponseEntity<?> registerVoter(@RequestPart("user") String userJson, @RequestPart("photo") MultipartFile photo) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            User user = mapper.readValue(userJson, User.class);
//            if (photo.isEmpty()) {
//                Path path = Paths.get(UPLOADED_FOLDER + DEFAULT_IMAGE);
//            }else {
//
//                byte[] bytes = photo.getBytes();
//                String renamedFilename = generateRenamedFilename(photo.getOriginalFilename());
//                Path path = Paths.get(UPLOADED_FOLDER + renamedFilename);
//                Files.write(path, bytes);
//
//                user.setImage(renamedFilename);
//            }
//
//            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
//
//            // Save the user to the database here
//            userService.addUser(user);
//            return new ResponseEntity<>("User registered successfully. " +
//                        "Please wait till your account gets verified by the admin and you will be notified via an email." , HttpStatus.OK);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    private String generateRenamedFilename(String originalFilename) {
//        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        return filenameWithoutExtension + "_" + timestamp + fileExtension;
//    }

    @PostMapping("/voter")
    public ResponseEntity<?> registerVoter(@RequestPart("user") User user, @RequestPart("photo") MultipartFile photo) {
        try {
            if (photo.isEmpty()) {
                return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
            } else {

                byte[] bytes = photo.getBytes();
                user.setImage(bytes); // Assuming you have a field to store the Base64 image in the User class.
            }

            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

            // Save the user to the database here
            userService.addUser(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully. Please wait until your account is verified by the admin, and you will be notified via email.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword){
        User user = userService.getUserById(changePassword.getUserId());

        if (user!=null) {
            if (bCryptPasswordEncoder.matches(changePassword.getOldPassword(), user.getPassword()))
                user.setPassword(this.bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
            else
                throw new PasswordDoesNotMatchException();

            return ResponseEntity.ok(userService.updateUser(user));
        }
        throw new RuntimeException();
    }


    @GetMapping("/voter/{email}")
    public ResponseEntity<?> getVoter(@PathVariable String email){
        return ResponseEntity.ok(userService.getUser(email));
    }

    @GetMapping("/admin/voters")
    public ResponseEntity<?> getAllVoter(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/voter/{email}")
    public void deleteUser(@PathVariable String email){
        userService.deleteUser(email);
    }

    // verification users
    @GetMapping("/admin/verify")
    public ResponseEntity<?> getVerificationVoterList(){
        return ResponseEntity.ok(verificationService.getValidationUsers());
    }

    @DeleteMapping("/admin/approved/{id}")
    public void deleteVerificationApproved(@PathVariable Long id){
        String message = "Documentation approved successfully.now you can login with your mail Id and password.";
        System.out.println(message);
        verificationService.deleteVerificationDetails(id,message);
    }

    @DeleteMapping("/admin/reject/{email}")
    public void deleteVerificationReject(@PathVariable String email){
        userService.deleteUser(email);
    }
 
    @PostMapping("/admin/reject/{email}")
    public void mailVerificationReject(@PathVariable String email,@RequestBody String message){
        verificationService.sendMail(message,email);
    }

    @GetMapping("/admin/verify/{id}")
    public Optional<VoterVerification> getVerificationVoter(@PathVariable Long id){
        return verificationService.getVerificationDetails(id);
    }
    
    @GetMapping(value="/dashbordInfo")
	public  Map<String, Object> dashbordInfo() {
		
		  Map<String, Object> hashMap = new HashMap<>();
			List<Election> lastElection = electionService.findlastThreeElections();
			List<User>  lastUser = userService.findlastThreeUser();
			   List<Election> elections = electionService.getAllElections();
		        int completed = 0;
		        int open = 0;
		        int yet = 0;
		        for (Election election : elections) {
		            if (LocalDateTime.now().isBefore(election.getStartDateTime())) {
		                yet++;
		            }
		            if (LocalDateTime.now().isAfter(election.getStartDateTime()) && LocalDateTime.now().isBefore(election.getEndDateTime())) {
		                open++;
		            } else {
		                completed++;
		            }
		        }

		    
			Long  ElectionCount= electionService.countElection();
			Long  VotesTotal= votesCounterService.countVotes();
			Long  candidatTotal= candidateService.countCandidat();
			Long  UserCount= userService.counUser();
		
			List<Object[]> countColisByDate = userService.countUserByDate();

		
			 hashMap.put("lastColis",lastElection);
			 hashMap.put("lastCient",lastUser);
			 hashMap.put("nonAcceptedColis",ElectionCount);
			 hashMap.put("totalVotes",VotesTotal);
			 hashMap.put("TotalElection", elections.size());
			 hashMap.put("OpenElection", open);
		     hashMap.put("PendingElection", yet);
			 hashMap.put("acceptedColisPayed",UserCount);
	
			 hashMap.put("countColisByDate",countColisByDate);

		return hashMap	;
	
}
}

