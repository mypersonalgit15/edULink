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
    private int courseCount;
    private int upcomingExamsCount;
//    private Long totalStudents;
//    private List<Course> myCourses;
//    private List<Exam> upcomingExamsList;
}
