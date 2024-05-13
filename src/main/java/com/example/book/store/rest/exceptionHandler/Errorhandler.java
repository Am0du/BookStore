package com.example.book.store.rest.exceptionHandler;

import com.example.book.store.rest.exception.*;
import com.example.book.store.rest.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class Errorhandler {

    private ErrorResponse errorResponse;

    @Autowired
    public Errorhandler(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    }

    public Errorhandler(){}

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserDoesNotHaveAuthority exc){
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFound exc){
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserDataNotComplete exc){
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc){
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidRole exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookEditNotPermitted exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookDoesNotExist exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(CommentNotFound exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());


        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage("Data integrity violation: " + ex.getRootCause().getMessage());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BookAlreadyExist exc) {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        errorResponse.setTimestamp(timestamp);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
