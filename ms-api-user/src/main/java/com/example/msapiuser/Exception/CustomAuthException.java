package com.example.msapiuser.Exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthException extends AuthenticationException {

    private final String message;

    public CustomAuthException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}