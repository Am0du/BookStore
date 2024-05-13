package com.example.book.store.rest.exception;

public class BookAlreadyExist extends RuntimeException{

    public BookAlreadyExist() {
    }

    public BookAlreadyExist(String message) {
        super(message);
    }

    public BookAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExist(Throwable cause) {
        super(cause);
    }

    public BookAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
