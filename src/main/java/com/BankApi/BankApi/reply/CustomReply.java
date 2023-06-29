package com.BankApi.BankApi.reply;

import com.BankApi.BankApi.model.Account;

public class CustomReply<T> {

    private int code;
    private String message;

    private T successData;

    public CustomReply(int code, String message, Account account) {
        this.code = code;
        this.message = message;
    }

    public CustomReply(int code, String message, T successData ) {
        this.code = code;
        this.message = message;
        this.successData = successData;
    }

    public CustomReply(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public T getSuccessData() {
        return successData;
    }

    public void setSuccessData(T successData) {
        this.successData = successData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}




