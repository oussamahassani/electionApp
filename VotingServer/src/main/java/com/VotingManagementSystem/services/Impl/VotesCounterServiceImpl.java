package com.VotingManagementSystem.services.Impl;

import com.VotingManagementSystem.controllers.VoterVotingStatusController;
import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;
import com.VotingManagementSystem.models.election.VotesCounter;
import com.VotingManagementSystem.repositories.ElectionRepository;
import com.VotingManagementSystem.repositories.VotesCounterRepository;
import com.VotingManagementSystem.services.CandidateService;
import com.VotingManagementSystem.services.UserService;
import com.VotingManagementSystem.services.VotesCounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class VotesCounterServiceImpl implements VotesCounterService {

    @Autowired
    private VotesCounterRepository votesCounterRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public void addVotesCounter(Candidate candidate) {
     VotesCounter list =  votesCounterRepository.findByCandidate(candidate);
     if (list==null){
         VotesCounter votesCounter = new VotesCounter();

         votesCounter.setCount(1);
         votesCounter.setCandidate(candidate);
         votesCounter.setElection(candidate.getElection());
         votesCounterRepository.save(votesCounter);
     }else {
             if (candidate == list.getCandidate()) {
                 list.setCount(list.getCount() + 1);
                 votesCounterRepository.save(list);
             }
     }

    }

    @Override
    public void removeAllVotesCounterByElection(Long id) {

        Election election = electionRepository.findById(id).orElse(null);

        if (election != null) {
            Set<VotesCounter> votesCounterSet = votesCounterRepository.findAllByElection(election);

            // Use iterator to avoid ConcurrentModificationException
            Iterator<VotesCounter> iterator = votesCounterSet.iterator();
            while (iterator.hasNext()) {
                VotesCounter voterVotingStatus = iterator.next();
                iterator.remove();
                votesCounterRepository.deleteByElection(election);
            }

            electionRepository.save(election);
        }
        List<VotesCounter> countersList = votesCounterRepository.findByElection(election);
        for (VotesCounter vc : countersList) {
            votesCounterRepository.deleteById(vc.getVotesCounterId());
        }
    }

    @Override
    public Map<String,?> getResultByElection(Election election) {

        List<VotesCounter> countersList = votesCounterRepository.findByElection(election);
        Long totalVotesReceived= 0L;
       
        for (VotesCounter votesCounter : countersList) {
           totalVotesReceived += votesCounter.getCount();

        }
       Optional<VotesCounter> maxCounter = countersList.stream()
                .max(Comparator.comparingLong(votesCounter ->votesCounter.getCandidate().getCandidate_id()));
     /*  if (maxCounter.isPresent()) {
      *     	    long maxCount = maxCounter.get().getCount();

    	    // Check if there are any other elements with the same max count
    	    boolean isUniqueMax = countersList.stream()
    	        .filter(vc -> vc.getCount() == maxCount)
    	        .count() == 1;

    	    if (!isUniqueMax) {
    	    	maxCounter  = Optional.empty() ;// Return empty if max is not unique
    	    }
       }*/
       
       // Step 1: Create a map of candidate IDs to their counts
       Map<Long, Long> candidateVoteCounts = countersList.stream()
           .collect(Collectors.groupingBy(
               votesCounter -> votesCounter.getCandidate().getCandidate_id(), // Group by candidate ID
               Collectors.counting() // Count occurrences
           ));

       // Step 2: Update the counts by adding +1 for each occurrence
       Map<Long, Long> updatedCandidateVoteCounts = candidateVoteCounts.entrySet().stream()
           .collect(Collectors.toMap(
               Map.Entry::getKey,
               entry -> entry.getValue()  // Add +1 to each count
           ));

       // Step 3: Find the candidate ID with the maximum count
       Optional<Map.Entry<Long, Long>> maxEntry = updatedCandidateVoteCounts.entrySet().stream()
           .max(Map.Entry.comparingByValue()); // Find entry with the maximum value

       // Optionally, handle the result

   
        Map<String,Object> map = new HashMap<>();
        maxEntry.ifPresent(entry -> {
            Long candidateId = entry.getKey();
            Long maxCount = entry.getValue();
            System.out.println("Candidate ID with max votes: " + candidateId + " (Votes: " + maxCount + ")");
            maxCounter.get().setCount(maxCount);
            Candidate candidate = candidateService.getCandidate(candidateId);
            maxCounter.get().setCandidate(candidate);;
            map.put("Winner",maxCounter);
        });
        map.put("totalVoter",userService.getAllUsers().size());
        map.put("totalCandidate",candidateService.getAllCandidateByElection(election.getElection_id()).size());
        map.put("CandidateVotes",countersList);
        map.put("totalVotesReceived",totalVotesReceived);
     
        return map;
    }

	@Override
	public Long countVotes() {
		// TODO Auto-generated method stub
		return votesCounterRepository.count();
	}
}
