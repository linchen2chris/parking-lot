package com.thoughtworks.parkinglot.exception;

public class NotFoundTicketException extends RuntimeException {
    private String exceptionMessage;

    public NotFoundTicketException(String exceptionMessage) {
        this.exceptionMessage  = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
