package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;

import java.util.Set;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

   Set<Candidate> findByElection(Election election);
}
