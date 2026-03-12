package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.projection.CourseProjection;

import java.util.List;

public interface ICourseService {
    String registerCourse(CourseRegistrationDto courseRegistrationDto);
    List<CourseProjection> findAllAvailableCourse();
}
