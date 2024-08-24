package com.hsbc.exceptions;

public class BugDoesNotExistException extends Exception{
    public BugDoesNotExistException() {
    }

    public BugDoesNotExistException(String message) {
        super(message);
    }
}
