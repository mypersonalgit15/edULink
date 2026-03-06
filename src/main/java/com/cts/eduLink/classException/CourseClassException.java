package com.cts.eduLink.classException;

public class CourseClassException extends RuntimeException{
    private String http;
    public CourseClassException(String msg){
        super(msg);
        this.http=http;
    }
}
