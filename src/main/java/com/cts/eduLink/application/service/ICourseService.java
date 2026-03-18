package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.projection.CourseProjection;

import java.util.List;
import java.util.Map;

public interface ICourseService {
    String registerCourse(CourseRegistrationDto courseRegistrationDto);
    List<CourseProjection> findAllAvailableCourse();
    String updateCourse(Long courseId, CourseRegistrationDto courseRegistrationDto);
    String patchCourse(Long courseId, Map<String, Object> updates);
    String deleteCourse(Long courseId);
    List<CourseProjection> getCoursesByFaculty(Long facultyId);
    public int getFacultyCourseCount(Long facultyId );
}
