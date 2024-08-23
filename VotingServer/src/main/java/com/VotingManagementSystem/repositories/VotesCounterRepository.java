package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;
import com.VotingManagementSystem.models.election.VotesCounter;

import java.util.List;
import java.util.Set;

public interface VotesCounterRepository extends JpaRepository<VotesCounter,Long> {

    public VotesCounter findByCandidate(Candidate candidate);

    public List<VotesCounter> findByElection(Election election);

    public Set<VotesCounter> findAllByElection(Election election);

    public void deleteByElection(Election election);

}
