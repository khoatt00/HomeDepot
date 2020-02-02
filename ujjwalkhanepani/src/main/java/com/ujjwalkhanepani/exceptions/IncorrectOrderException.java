package com.ujjwalkhanepani.exceptions;

public class IncorrectOrderException extends Exception {

    public IncorrectOrderException(String message) {
        super(message);
    }

    public IncorrectOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectOrderException(Throwable cause) {
        super(cause);
    }
}
