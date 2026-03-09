package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final IExamService examService;

    @Autowired
    public ExamController(IExamService examService) {
        this.examService = examService;
    }

    // Get exam for course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Exam> getExamForCourse(@PathVariable Long courseId) {
        Exam exam = examService.getExamByCourseId(courseId);
        return ResponseEntity.ok(exam);
    }

    // Submit exam
    @PostMapping("/submit/{examId}")
    public ResponseEntity<String> submitExam(@PathVariable Long examId, @RequestBody List<String> answers, @RequestParam Long studentId) {
        examService.submitExam(examId, studentId, answers);
        return ResponseEntity.ok("Exam Completed Successfully");
    }
}