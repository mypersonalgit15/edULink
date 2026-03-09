package com.cts.eduLink.application.globalexception;

import com.cts.eduLink.application.classException.CourseClassException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException extends RuntimeException{
    @ExceptionHandler(CourseClassException.class)
    public String courseException(CourseClassException e){
        return e.getMessage();
    }
}
