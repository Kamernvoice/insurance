package com.example.springsecurityjwt.exception;

public class CustomNotFoundException extends Exception{

    private final String message;

    public CustomNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
