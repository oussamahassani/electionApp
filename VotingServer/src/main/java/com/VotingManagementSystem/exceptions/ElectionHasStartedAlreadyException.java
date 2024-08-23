package com.VotingManagementSystem.exceptions;

public class ElectionHasStartedAlreadyException extends RuntimeException{

    public ElectionHasStartedAlreadyException() {
        super("Election does not exist !!");
    }

    public ElectionHasStartedAlreadyException(String message) {
        super(message);
    }
}
