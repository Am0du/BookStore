package com.example.book.store.rest.exception;

public class InvalidRole extends RuntimeException{
    public InvalidRole() {
    }

    public InvalidRole(String message) {
        super(message);
    }

    public InvalidRole(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRole(Throwable cause) {
        super(cause);
    }

    public InvalidRole(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
