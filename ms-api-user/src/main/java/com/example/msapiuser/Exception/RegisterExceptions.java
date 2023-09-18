package com.example.msapiuser.Exception;

public class RegisterExceptions extends RuntimeException {
    private final String message;
    private final String detailMessage;

    public RegisterExceptions(String message, String detailMessage) {
        super(message);
        this.message = message;
        this.detailMessage = detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return detailMessage;
    }
}
