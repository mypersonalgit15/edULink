package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.projection.CourseProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements ICourseService{
    private final CourseRepository courseRepository;

    @Override
    public String registerCourse(CourseRegistrationDto courseRegistrationDto) {
        return "";
    }

    @Override
    public List<CourseProjection> findAllAvailableCourse() throws CourseException {
        log.info("User has requested to display course List");
        List<CourseProjection> courseProjections = courseRepository.findAllAvailableCourse();
        if(courseProjections.isEmpty()){
            log.error("no course is available to display");
            throw new CourseException("No course Available!", HttpStatus.NOT_FOUND);
        }
        log.info("Course List has been accessed SuccessFully and first course name is {}",courseProjections.getFirst().getCourseTitle());
        return courseProjections;
    }
}
