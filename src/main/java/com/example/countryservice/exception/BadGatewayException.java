package com.example.countryservice.exception;

public class BadGatewayException extends RuntimeException {
    public BadGatewayException(String msg) {
        super(msg);
    }
}