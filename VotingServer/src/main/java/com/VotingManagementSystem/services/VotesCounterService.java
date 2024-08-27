package com.VotingManagementSystem.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VotesCounter;

public interface VotesCounterService {

    public void addVotesCounter(Candidate candidate);
    public void removeAllVotesCounterByElection(Long id);
    public Map<String,?> getResultByElection(Election election);
	public Long countVotes();
	public List<Object[]> countElectionByDate(Long eid);
}
