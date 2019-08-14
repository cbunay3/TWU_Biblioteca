package com.twu.library.exception;

public class AuthorizationFailedException extends RuntimeException {
    public AuthorizationFailedException(String message) {
        super(message);
    }
}
