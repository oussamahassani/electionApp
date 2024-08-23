package com.VotingManagementSystem.services;

import java.util.List;
import java.util.Optional;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.VoterVerification;

public interface VoterVerificationService {

    public VoterVerification addVerificationDetails(VoterVerification verification);
    public Optional<VoterVerification> getVerificationDetails(Long id);

    public List<VoterVerification> getValidationUsers();

    public void deleteVerificationDetails(Long id,String message);
    public void sendMail(String message,String email);
}
