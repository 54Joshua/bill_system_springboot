package com.hcb.newBilly.common;

public class BillyException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BillyException(String message) {
        super(message);
        this.message=message;
    }
}
