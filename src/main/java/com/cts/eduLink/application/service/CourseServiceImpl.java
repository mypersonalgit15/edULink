package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.classexception.FacultyException;
import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.projection.CourseDetailByIdProjection;
import com.cts.eduLink.application.projection.CourseDetailProjection;
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
        log.error("Unable to separate faculty from courseRegistrationDto");
        Course course = ClassSeparatorUtils.facultyDtoSeparator(courseRegistrationDto);
        course.setCourseStatus("ACTIVE");
        course.getFacultySet().add(facultyOption.get());
        facultyOption.get().getCourseSet().add(course);
        courseRepository.save(course);
        log.info("Course with id {} saved successFully into database",course.getCourseId());
        return "Course has registered successFully with course Id: "+course.getCourseId();
    }

    @Override
    public List<CourseDetailProjection> findAllAvailableCourse() throws CourseException {
        log.info("User has requested to display course List");
        List<CourseDetailProjection> courseDetailProjections = courseRepository.findAllAvailableCourse();
        if(courseDetailProjections.isEmpty()){
            log.error("no course is available to display");
            throw new CourseException("No course Available!", HttpStatus.NOT_FOUND);
        }
        log.info("Course List has been accessed SuccessFully and first course name is {}", courseDetailProjections.getFirst().getCourseTitle());
        return courseDetailProjections;
    }

    @Override
    public CourseDetailByIdProjection findCourseDetailsById(Long courseId) throws CourseException{
        Optional<CourseDetailByIdProjection> courseDetailByIdProjection = courseRepository.findCourseDetailsById(courseId);
        if (courseDetailByIdProjection.isEmpty()){
            log.debug("User requested for {} which is not available",courseId);
            throw new CourseException("No Course available for course id: "+courseId,HttpStatus.NOT_FOUND);
        }
        log.debug("{} has fetched successfully from course table",courseId);
        return courseDetailByIdProjection.get();
    }
}
