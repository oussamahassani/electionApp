package com.VotingManagementSystem.controllers;

import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.services.ElectionService;
import com.VotingManagementSystem.services.VoterVotingStatusService;
import com.VotingManagementSystem.services.VotesCounterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/elections")
@CrossOrigin("*")
public class ElectionController {

    @Autowired
    private ElectionService electionService;
    
    @Autowired
    private VoterVotingStatusService voterVotingStatusService;
    
    @Autowired
    private VotesCounterService votesCounterService ;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewElection(@RequestBody Election election){
        return ResponseEntity.ok(electionService.addElection(election));
    }



    @GetMapping("/")
    public ResponseEntity<?> getAllElections(){
        return ResponseEntity.ok(electionService.getAllElections());
    }
    @GetMapping("/notNow")
    public ResponseEntity<?> getAllElectionsnotNow(){
        return ResponseEntity.ok(electionService.getAllElectionsnotNow());
    }
    
    @GetMapping("/{eid}")
    public ResponseEntity<?> getElection(@PathVariable Long eid){
        return ResponseEntity.ok(electionService.getElection(eid));
    }

    @DeleteMapping("/{eid}")
    public void deleteElection(@PathVariable Long eid){
        electionService.deleteElection(eid);
    }
    
    @GetMapping(value="/dashbordInfo/{eid}")
	public  Map<String, Object> dashbordInfo(@PathVariable Long eid) {
  	  Map<String, Object> hashMap = new HashMap<>();
  	List<Object[]> countElectionByDate = votesCounterService.countElectionByDate(eid);
	List<Object[]> countElectionByGender= voterVotingStatusService.countElectionByGender(eid);
	List<Object[]> countElectionByGouvernerat= voterVotingStatusService.countElectionByGouvernerat(eid);
	
    hashMap.put("countElectionByDate", countElectionByDate);
		 hashMap.put("countElectionByGender",countElectionByGender);

		 hashMap.put("countElectionByGouvernerat",countElectionByGouvernerat);

	return hashMap	;
    }
}
