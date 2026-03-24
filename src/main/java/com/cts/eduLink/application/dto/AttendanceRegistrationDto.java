package com.cts.eduLink.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AttendanceRegistrationDto {
    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
