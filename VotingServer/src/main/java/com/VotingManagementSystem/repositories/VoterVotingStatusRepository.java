package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;

import java.util.List;
import java.util.Set;

public interface VoterVotingStatusRepository extends JpaRepository<VoterVotingStatus,Long> {

    public void deleteByElection(Election election);

    public Set<VoterVotingStatus> findAllByElection(Election election);
}
