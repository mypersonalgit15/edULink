package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.projection.CourseProjection;
import com.cts.eduLink.application.service.ICourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@Slf4j
public class CourseController {

    private final ICourseService iCourseService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(@RequestBody CourseRegistrationDto courseRegistrationDto){
        return  ResponseEntity.status(200).body(iCourseService.registerCourse(courseRegistrationDto));
    }

    @GetMapping("/findAllAvailableCourse")
    public ResponseEntity<List<CourseProjection>> findALlAvailableCourse(){
        log.info("User has called the endpoint successFully to fetch all available courses");
        return ResponseEntity.status(200).body(iCourseService.findAllAvailableCourse());
    }

    @GetMapping("/courses/{facultyId}")
    public ResponseEntity<List<CourseProjection>> getCoursesByFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.status(200).body(iCourseService.getCoursesByFaculty(facultyId));
    }

    @GetMapping("/courseCount/{facultyId}")
    public Map<String, Integer> getFacultyCourseCount(@PathVariable Long facultyId) {
        int count = iCourseService.getFacultyCourseCount(facultyId);
        return Map.of("My Courses", count);
    }
}
