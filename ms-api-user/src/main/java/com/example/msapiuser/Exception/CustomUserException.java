package com.example.msapiuser.Exception;

public class CustomUserException extends RuntimeException {
    private final String message;

    public CustomUserException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
