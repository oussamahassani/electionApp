package com.VotingManagementSystem.exceptions;

public class CandidateNotFoundException extends RuntimeException{

    CandidateNotFoundException() {
        super("Candidate not found");
    }

    public CandidateNotFoundException(String message) {
        super(message);
    }
}
