package com.BankApi.BankApi.errorException;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;
    private HttpStatus status;

    public ErrorResponse(int value, String message, long currentTimeMillis) {
    }
}