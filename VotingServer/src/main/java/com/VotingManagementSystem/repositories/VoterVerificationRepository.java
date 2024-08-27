package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.VoterVerification;

public interface VoterVerificationRepository extends JpaRepository<VoterVerification,Long> {

    VoterVerification findByUser(User user);

    @Query(value = "SELECT * from voter_verification where user_user_id = :userId ", nativeQuery = true)
	VoterVerification findByIdUser(Long userId);


}
