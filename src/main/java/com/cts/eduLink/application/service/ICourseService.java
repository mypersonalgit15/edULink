package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.*;
import java.util.List;

public interface ICourseService {
    Course getCourseById(Long courseId);
    void enrollStudentInCourse(Long studentId, Long courseId);
    List<LearningMaterial> getCourseMaterials(Long courseId);
}
