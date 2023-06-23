package com.BankApi.BankApi.errorException;

public class DuplicateAddressException extends RuntimeException{
    public DuplicateAddressException(String message) {
        super(message);
    }
}
