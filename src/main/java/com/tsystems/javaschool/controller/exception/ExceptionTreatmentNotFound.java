package com.tsystems.javaschool.controller.exception;

public class ExceptionTreatmentNotFound extends Exception{
    private String message;

    public ExceptionTreatmentNotFound() {
        super();
    }

    public ExceptionTreatmentNotFound(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
