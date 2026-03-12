package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.classexception.FacultyException;
import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.projection.CourseProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.util.ClassSeparatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements ICourseService{
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public String registerCourse(CourseRegistrationDto courseRegistrationDto) throws FacultyException {
        log.info("Course registration has intercepted inside service");
        Optional<Faculty> facultyOption = facultyRepository.findFacultyById(courseRegistrationDto.getFacultyId());
        if(facultyOption.isEmpty()){
            log.error("{} is not authorized to register course",courseRegistrationDto.getFacultyId());
            throw new FacultyException(courseRegistrationDto.getFacultyId()+" is not registered",HttpStatus.BAD_REQUEST);
        }
        Course course = ClassSeparatorUtils.facultyDtoSeparator(courseRegistrationDto);
        course.setCourseStatus("ACTIVE");
        course.getFacultySet().add(facultyOption.get());
        courseRepository.save(course);
        return "Course has registered successFully with course Id: "+course.getCourseId();
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
