package com.cts.eduLink.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GradeRegistrationDto {
    @NotNull(message = "Student ID is required to assign a score")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @DecimalMin(value = "0.0", message = "Score cannot be negative")
    @DecimalMax(value = "100.0", message = "Score cannot exceed 100.0")
    private double score;
}
