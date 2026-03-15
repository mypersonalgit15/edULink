package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}