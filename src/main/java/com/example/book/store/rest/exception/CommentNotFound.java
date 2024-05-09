package com.example.book.store.rest.exception;

public class CommentNotFound extends RuntimeException{
    public CommentNotFound() {
    }

    public CommentNotFound(String message) {
        super(message);
    }

    public CommentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentNotFound(Throwable cause) {
        super(cause);
    }

    public CommentNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
