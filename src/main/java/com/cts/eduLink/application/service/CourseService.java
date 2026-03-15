package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.*;
import com.cts.eduLink.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final LearningMaterialRepository learningMaterialRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                        StudentRepository studentRepository,
                        LearningMaterialRepository learningMaterialRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.learningMaterialRepository = learningMaterialRepository;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    @Transactional
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = getCourseById(courseId);
        student.getCourseSet().add(course);
        studentRepository.save(student);
    }

    @Override
    public List<LearningMaterial> getCourseMaterials(Long courseId) {
        Course course = getCourseById(courseId);
        return course.getLearningMaterialList();
    }
}
    @Override
    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(() -> new RuntimeException("Faculty not found"));
    }
}