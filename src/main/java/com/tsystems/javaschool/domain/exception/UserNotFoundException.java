package com.tsystems.javaschool.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
