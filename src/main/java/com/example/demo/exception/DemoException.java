package com.example.demo.exception;

public class DemoException extends RuntimeException {
    private final String errorCode;

    public DemoException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode() { return errorCode; }
}