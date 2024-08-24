package com.hsbc.exceptions;

public class IncorrectUsernameOrPasswordException extends Exception {
    public IncorrectUsernameOrPasswordException() {
    }

    public IncorrectUsernameOrPasswordException(String message) {
        super(message);
    }
}
