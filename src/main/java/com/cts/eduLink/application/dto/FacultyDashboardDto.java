package com.cts.eduLink.application.dto;

import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Exam;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDashboardDto {
    @NotNull(message = "Course count cannot be null")
    @Min(value = 1, message = "Course count cannot be negative")
    private int courseCount;

    @NotNull(message = "Upcoming exams count cannot be null")
    @Min(value = 1, message = "Exams count cannot be negative")
    private int upcomingExamsCount;
}
