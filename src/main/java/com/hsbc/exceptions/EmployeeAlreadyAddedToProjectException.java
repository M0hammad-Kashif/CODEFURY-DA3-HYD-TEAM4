package com.hsbc.exceptions;

public class EmployeeAlreadyAddedToProjectException extends Exception{
    public EmployeeAlreadyAddedToProjectException() {
    }

    public EmployeeAlreadyAddedToProjectException(String message) {
        super(message);
    }
}
