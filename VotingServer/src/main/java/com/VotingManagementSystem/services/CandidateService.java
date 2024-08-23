package com.VotingManagementSystem.services;

import java.util.Set;

import com.VotingManagementSystem.models.election.Candidate;
import com.VotingManagementSystem.models.election.Election;

public interface CandidateService {

    public Candidate addCandidate(Candidate candidate);
    public void deleteCandidate(Long id);

    public Candidate getCandidate(Long id);
    public Set<Candidate> getAllCandidates();

    public Set<Candidate> getAllCandidateByElection(Long Election_id);

    public Candidate updateCandidate(Candidate candidate);
}
