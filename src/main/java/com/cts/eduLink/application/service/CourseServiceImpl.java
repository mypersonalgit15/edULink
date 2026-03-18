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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements ICourseService{
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Override
    @Transactional
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
        facultyOption.get().getCourseSet().add(course);
        courseRepository.save(course);
        return "Course has registered successFully with course Id: "+course.getCourseId();
    }

    @Override
    @Transactional
    public String updateCourse(Long courseId, CourseRegistrationDto courseRegistrationDto) {
        log.info("Updating course details for Course ID: {}", courseId);

        // 1. Find existing course or throw exception
        Course existingCourse = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CourseException("Course not found with ID: " + courseId, HttpStatus.NOT_FOUND));

        // 2. Map DTO fields to existing entity using utility
        ClassSeparatorUtils.updateCourseFromDto(existingCourse, courseRegistrationDto);

        // 3. Save the updated entity
        courseRepository.save(existingCourse);

        log.info("Course ID: {} updated successfully", courseId);
        return "Course updated successfully!";
    }

    @Override
    @Transactional
    public String patchCourse(Long courseId, Map<String, Object> updates) {
        log.info("Patch update initiated for Course ID: {}", courseId);

        // 1. Find the existing course
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new CourseException("Course not found with ID: " + courseId, HttpStatus.NOT_FOUND));

        // 2. Iterate through the map and update only provided fields
        updates.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "courseTitle":
                        course.setCourseTitle(value.toString());
                        break;
                    case "courseSubject":
                        course.setCourseSubject(value.toString());
                        break;
                    case "courseGradeLevel":
                        course.setCourseGradeLevel(value.toString());
                        break;
                    case "courseCredit":
                        course.setCourseCredit(Integer.parseInt(value.toString()));
                        break;
                    case "courseStatus":
                        course.setCourseStatus(value.toString());
                        break;
                }
            }
        });

        // 3. Save the partially updated entity
        courseRepository.save(course);
        log.info("Course ID: {} partially updated successfully", courseId);
        return "Course partially updated successfully!";
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public String deleteCourse(Long courseId) {
        log.info("Deletion request initiated for Course ID: {}", courseId);

        // 1. Find the existing course or throw exception
        com.cts.eduLink.application.entity.Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new com.cts.eduLink.application.classexception.CourseException(
                        "Course not found with ID: " + courseId,
                        org.springframework.http.HttpStatus.NOT_FOUND
                ));

        // 2. Delete the course
        courseRepository.delete(course);

        log.info("Course ID: {} deleted successfully", courseId);
        return "Course deleted successfully!";
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

    @Override
    public List<CourseProjection> getCoursesByFaculty(Long facultyId) {
        return courseRepository.findCoursesByFacultyId(facultyId);
    }

    public int getFacultyCourseCount(Long facultyId ){
        int count = courseRepository.getFacultyCourseCount(facultyId);
        return count;
    }
}
