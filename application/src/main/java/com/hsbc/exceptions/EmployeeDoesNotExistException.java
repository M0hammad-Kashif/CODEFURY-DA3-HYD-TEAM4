package com.hsbc.exceptions;

public class EmployeeDoesNotExistException extends Exception{
    public EmployeeDoesNotExistException() {
    }

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }
}
