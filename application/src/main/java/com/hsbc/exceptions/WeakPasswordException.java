package com.hsbc.exceptions;

public class WeakPasswordException extends Exception {
    public WeakPasswordException() {
    }

    public WeakPasswordException(String message) {
        super(message);
    }
}
