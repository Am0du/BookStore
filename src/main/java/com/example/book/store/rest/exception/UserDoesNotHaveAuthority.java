package com.example.book.store.rest.exception;

public class UserDoesNotHaveAuthority extends RuntimeException{

    public UserDoesNotHaveAuthority(String message) {
        super(message);
    }

    public UserDoesNotHaveAuthority(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDoesNotHaveAuthority(Throwable cause) {
        super(cause);
    }
}
