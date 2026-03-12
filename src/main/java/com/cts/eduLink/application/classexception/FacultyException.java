package com.cts.eduLink.application.classexception;

import org.springframework.http.HttpStatus;

public class FacultyException extends RuntimeException {
    public final HttpStatus httpStatus;
    public FacultyException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
