package com.VotingManagementSystem.services;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;

import java.util.List;

public interface VoterVotingStatusService {

    public VoterVotingStatus addVoterVotingStatus(VoterVotingStatus voterVotingStatus);
    public void removeAllVoterVotingStatusByElection(Long eid);
    public boolean getVoterVotingStatus(User user, Election election);
    public List<VoterVotingStatus> getAll();

	public List<Object[]> countElectionByGender(Long eid);
	public List<Object[]> countElectionByGouvernerat(Long eid);

}
