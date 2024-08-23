package com.VotingManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.VoterVerification;

public interface VoterVerificationRepository extends JpaRepository<VoterVerification,Long> {

    VoterVerification findByUser(User user);
}
