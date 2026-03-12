package com.cts.eduLink.application.repositoryTest;

import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.projection.CourseProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
//@ActiveProfiles("test")
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    private Course course;

    @BeforeEach
    public void setUp(){
        course = new Course();
        course.setCourseId(12532434543L);
        course.setCourseTitle("Java Full Stack");
        course.setCourseSubject("Java");
        course.setCourseGradeLevel("Intermediate");
        course.setCourseCredit(4);
        course.setCourseStatus("Active");
        course.setCourseRating(4.5);
        courseRepository.save(course);
    }


    @Test
    public void getCourseList_200(){
        List<CourseProjection> courseProjections = courseRepository.findAllAvailableCourse();
        assertEquals("Java",courseProjections.get(0).getCourseSubject());
    }
}
