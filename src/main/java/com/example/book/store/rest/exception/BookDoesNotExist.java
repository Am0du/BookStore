package com.example.book.store.rest.exception;

public class BookDoesNotExist extends RuntimeException{
    public BookDoesNotExist() {
    }

    public BookDoesNotExist(String message) {
        super(message);
    }

    public BookDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDoesNotExist(Throwable cause) {
        super(cause);
    }

    public BookDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
