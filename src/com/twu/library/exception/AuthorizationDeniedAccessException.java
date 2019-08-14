package com.twu.library.exception;

public class AuthorizationDeniedAccessException extends Throwable {
    public AuthorizationDeniedAccessException(String message) {
        super(message);
    }
}
