package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.projection.CourseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(" select new com.cts.eduLink.application.projection.CourseProjection(c.courseTitle," +
            "c.courseSubject,c.courseGradeLevel,c.courseCredit,c.courseStatus,c.courseRating) from Course c")
    List<CourseProjection> findAllAvailableCourse();
}
