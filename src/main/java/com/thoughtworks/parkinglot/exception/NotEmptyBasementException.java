package com.thoughtworks.parkinglot.exception;

public class NotEmptyBasementException extends RuntimeException {
    private String exceptionMessage;

    public NotEmptyBasementException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
