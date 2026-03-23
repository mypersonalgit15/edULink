package com.cts.eduLink.application.classexception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.net.http.HttpClient;
@Getter
public class FileException extends  RuntimeException{
    private final HttpStatus httpStatus;
    public FileException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
    }
}
