package com.thoughtworks.parkinglot.exception;

public class NotFoundTicket extends RuntimeException {
    private String exceptionMessage;

    public NotFoundTicket(String exceptionMessage) {
        this.exceptionMessage  = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
