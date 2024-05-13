package com.example.book.store.rest.response;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ErrorResponse {

    private int status;
    private String message;

    private OffsetDateTime timestamp;

    public ErrorResponse(){}

    public ErrorResponse(int status, String message, OffsetDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
