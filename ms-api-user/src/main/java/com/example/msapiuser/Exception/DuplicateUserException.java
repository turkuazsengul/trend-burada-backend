package com.example.msapiuser.Exception;

public class DuplicateUserException extends RegisterExceptions{
    private final String message;
    private final String detailMessage;

    public DuplicateUserException(String message,String detailMessage) {
        super(message, detailMessage);
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
