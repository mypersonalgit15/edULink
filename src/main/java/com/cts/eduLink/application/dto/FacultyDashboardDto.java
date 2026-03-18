package com.cts.eduLink.application.dto;

import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDashboardDto {
    private Long totalStudents;
    private Long totalCourses;
    private Long upcomingExams;
//    private List<Course> myCourses;
//    private List<Exam> upcomingExamsList;
}
