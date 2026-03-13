package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.projection.CourseDetailByIdProjection;
import com.cts.eduLink.application.projection.CourseDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(" select new com.cts.eduLink.application.projection.CourseDetailProjection(c.courseTitle," +
            "c.courseGradeLevel,c.courseRating) from Course c")
    List<CourseDetailProjection> findAllAvailableCourse();

    @Query(" select new com.cts.eduLink.application.projection.CourseDetailByIdProjection(c.courseTitle," +
            "c.courseSubject,c.courseGradeLevel,c.courseCredit,c.courseStatus,c.courseRating) from Course c where c.courseId = :courseId")
    Optional<CourseDetailByIdProjection> findCourseById(@Param("courseId") Long courseId);
}
