package com.thoughtworks.parkinglot.exception;

public class NoFreeStaffException extends RuntimeException {
    private String exceptionMessage;

    public NoFreeStaffException(String exceptionMessage) {this.exceptionMessage = exceptionMessage;}

    public String getExceptionMessage() {return this.exceptionMessage;}

}
