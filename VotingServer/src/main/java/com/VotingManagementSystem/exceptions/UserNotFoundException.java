package com.VotingManagementSystem.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User already exist");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
