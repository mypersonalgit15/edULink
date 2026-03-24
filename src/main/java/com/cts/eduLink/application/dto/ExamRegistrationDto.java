package com.cts.eduLink.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExamRegistrationDto {

    @NotNull(message = "Course ID is required for enrollment")
    private Long courseId;

    @NotNull(message = "Exam Name is required for enrollment")
    private String examName;

}
