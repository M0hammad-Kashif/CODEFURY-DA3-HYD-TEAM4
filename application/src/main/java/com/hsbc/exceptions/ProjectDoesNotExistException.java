package com.hsbc.exceptions;

public class ProjectDoesNotExistException extends Exception{
    public ProjectDoesNotExistException() {
    }

    public ProjectDoesNotExistException(String message) {
        super(message);
    }
}
