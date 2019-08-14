package com.twu.library.exception;

public class AuthenticationRequiredException extends RuntimeException {
    public AuthenticationRequiredException(String message) {
        super(message);
    }
}
