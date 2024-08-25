package com.VotingManagementSystem.controllers;

import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.services.ElectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/elections")
@CrossOrigin("*")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewElection(@RequestBody Election election){
        return ResponseEntity.ok(electionService.addElection(election));
    }



    @GetMapping("/")
    public ResponseEntity<?> getAllElections(){
        return ResponseEntity.ok(electionService.getAllElections());
    }

    @GetMapping("/{eid}")
    public ResponseEntity<?> getElection(@PathVariable Long eid){
        return ResponseEntity.ok(electionService.getElection(eid));
    }

    @DeleteMapping("/{eid}")
    public void deleteElection(@PathVariable Long eid){
        electionService.deleteElection(eid);
    }
}
