package com.cts.eduLink.application.initializer;

import com.cts.eduLink.application.entity.*;
import com.cts.eduLink.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final LearningMaterialRepository learningMaterialRepository;
    private final AssignmentStatusRepository assignmentStatusRepository;
    private final ExamRepository examRepository;

    @Autowired
    public DataInitializer(CourseRepository courseRepository,
                          StudentRepository studentRepository,
                          FacultyRepository facultyRepository,
                          LearningMaterialRepository learningMaterialRepository,
                          AssignmentStatusRepository assignmentStatusRepository,
                          ExamRepository examRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.learningMaterialRepository = learningMaterialRepository;
        this.assignmentStatusRepository = assignmentStatusRepository;
        this.examRepository = examRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("DataInitializer.run() called!");
        long count = courseRepository.count();
        logger.info("Current course count in database: {}", count);
        
        if (count == 0) {
            logger.info("Database is empty, initializing test data...");
            initializeData();
        } else {
            logger.info("Database already has data, skipping initialization");
        }
    }

    private void initializeData() {
        logger.info("Starting data initialization with JPQL...");
        // Create Course
        Course course = new Course();
        course.setCourseId(101L);
        course.setCourseTitle("Web Development Bootcamp");
        course.setCourseSubject("Computer Science");
        course.setCourseGradeLevel("Beginner");
        course.setCourseCredit(3);
        course.setCourseStatus("Active");
        course.setCourseRating(4.5);
        Course savedCourse = courseRepository.save(course);

        // Create Faculty
        Faculty faculty = new Faculty();
        faculty.setFacultyId(201L);
        faculty.setFacultyGender("Male");
        faculty.setStudentAddress("Faculty Address 1");
        faculty.setFacultyYearOfExperience(10);
        faculty.setFacultyRating(4.8);
        Faculty savedFaculty = facultyRepository.save(faculty);

        // Create Student
        Student student = new Student();
        student.setStudentId(301L);
        student.setStudentDOB(LocalDate.of(2000, 1, 1));
        student.setStudentGender("Male");
        student.setStudentAddress("Address 1");
        student.setStudentEnrollmentDateTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        Student savedStudent = studentRepository.save(student);

        // Create Learning Materials
        LearningMaterial material1 = new LearningMaterial();
        material1.setLearningMaterialTitle("Module 1 - Introduction.pdf");
        material1.setLearningMaterialUploadedDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        material1.setLearningMaterialStatus("Active");
        material1.setCourse(savedCourse);
        learningMaterialRepository.save(material1);

        LearningMaterial material2 = new LearningMaterial();
        material2.setLearningMaterialTitle("Module 2 - Basics.pdf");
        material2.setLearningMaterialUploadedDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        material2.setLearningMaterialStatus("Active");
        material2.setCourse(savedCourse);
        learningMaterialRepository.save(material2);

        // Create Assignment Statuses
        for (int i = 0; i < 5; i++) {
            AssignmentStatus assignment = new AssignmentStatus();
            assignment.setStatus("Pending");
            assignment.setStudent(savedStudent);
            assignment.setCourse(savedCourse);
            assignmentStatusRepository.save(assignment);
        }

        // Create Exam
        Exam exam = new Exam();
        logger.info("✓ Test data initialized successfully using JPQL!");
        exam.setExamName("Web Development Exam");
        exam.setExamLocalDateTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        exam.setExamStatus("Active");
        exam.setCourse(savedCourse);
        examRepository.save(exam);

        System.out.println("✓ Test data initialized successfully using JPQL!");
    }
}
