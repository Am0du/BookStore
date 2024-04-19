package com.example.book.store.rest.exception;

public class UserDataNotComplete extends RuntimeException{
    public UserDataNotComplete() {
    }

    public UserDataNotComplete(String message) {
        super(message);
    }

    public UserDataNotComplete(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDataNotComplete(Throwable cause) {
        super(cause);
    }

    public UserDataNotComplete(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
