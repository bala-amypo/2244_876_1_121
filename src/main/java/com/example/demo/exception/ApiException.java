package com.example.demo.exception;

public class ApiException extends RuntimeException {

    // Constructor with message
    public ApiException(String message) {
        super(message);
    }

    // Optional: no-args constructor
    public ApiException() {
        super();
    }
}
