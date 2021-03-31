package com.thoughtworks.parkinglot.exception;

public class NoAvailableGarageException extends RuntimeException{
    private String exceptionMessage;

    public NoAvailableGarageException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
