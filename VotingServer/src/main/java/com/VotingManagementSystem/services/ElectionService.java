package com.VotingManagementSystem.services;

import java.util.List;
import java.util.Set;

import com.VotingManagementSystem.models.election.Election;

public interface ElectionService {

    public Election addElection(Election election);
    public void deleteElection(Long id);
    public Election updateElection(Election election);

    public Election getElection(Long id);
    public List<Election> getAllElections();
    
    public List<Election> findlastThreeElections();
	public Long countElection();


}
