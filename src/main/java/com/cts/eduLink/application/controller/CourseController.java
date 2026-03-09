package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.entity.*;
import com.cts.eduLink.application.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollInCourse(@PathVariable Long courseId, @RequestParam Long studentId) {
        courseService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok("Successfully Enrolled in the Course");
    }

    @GetMapping("/{courseId}/content")
    public ResponseEntity<List<LearningMaterial>> getCourseContent(@PathVariable Long courseId) {
        List<LearningMaterial> materials = courseService.getCourseMaterials(courseId);
        return ResponseEntity.ok(materials);
    }
}