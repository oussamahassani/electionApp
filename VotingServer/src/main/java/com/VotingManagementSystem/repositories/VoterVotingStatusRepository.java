package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;

import java.util.List;
import java.util.Set;

public interface VoterVotingStatusRepository extends JpaRepository<VoterVotingStatus,Long> {

    public void deleteByElection(Election election);

    public Set<VoterVotingStatus> findAllByElection(Election election);
    
    @Query(value = "SELECT user.gender AS gender, COUNT(*) AS count " +
            "FROM voter_voting_status " +
            "JOIN user ON voter_voting_status.user_user_id  = user.user_id  " +
            "GROUP BY user.gender " +
            "ORDER BY gender", nativeQuery = true)
List<Object[]> countVotesByGender();

@Query(value = "SELECT user.gouvernerat AS gouvernerat, COUNT(*) AS count " +
        "FROM voter_voting_status " +
        "JOIN user ON voter_voting_status.user_user_id  = user.user_id  " +
        "GROUP BY user.gouvernerat " +
        "ORDER BY gouvernerat", nativeQuery = true)
List<Object[]> countVotesByGouvernerat();
}
