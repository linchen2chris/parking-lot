package com.thoughtworks.parkinglot.exception;


public class NotFoundCarException extends RuntimeException {
    private final String exceptionMessage;

    public NotFoundCarException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
