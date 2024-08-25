package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    
    @Query(value = "SELECT YEAR(created_date) AS year, MONTH(created_date) AS month,HOUR(created_date) AS hour, COUNT(*) AS count FROM votes_counter GROUP BY YEAR(created_date), MONTH(created_date) ,HOUR(created_date)  ORDER BY year, month , hour" , nativeQuery = true)

	List<Object[]> countVoteByDate();

}
