package com.hsbc.exceptions;

public class BugAlreadyClosedException extends Exception{
    public BugAlreadyClosedException() {
    }

    public BugAlreadyClosedException(String message) {
        super(message);
    }
}
