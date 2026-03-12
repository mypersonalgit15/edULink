package com.cts.eduLink.application.dto;

import lombok.Getter;

@Getter
public class CourseRegistrationDto {
    private String courseTitle;
    private String courseSubject;
    private String courseGradeLevel;
    private int courseCredit;
    private String courseStatus;
    private Long facultyId;
}
