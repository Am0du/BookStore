package com.example.book.store.rest.exception;

public class BookEditNotPermitted extends RuntimeException{
    public BookEditNotPermitted() {
    }

    public BookEditNotPermitted(String message) {
        super(message);
    }

    public BookEditNotPermitted(String message, Throwable cause) {
        super(message, cause);
    }

    public BookEditNotPermitted(Throwable cause) {
        super(cause);
    }

    public BookEditNotPermitted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
