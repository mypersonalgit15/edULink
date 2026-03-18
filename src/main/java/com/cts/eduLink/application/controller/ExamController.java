package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.classexception.ExamException;
import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.projection.ExamProjection;
import com.cts.eduLink.application.service.IExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@Slf4j
public class ExamController {

    private final IExamService examService;

    public ExamController(IExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createExam(@RequestBody ExamCreationRequestDto request) {
        log.info("Creating a new exam: {}", request.getExamName());
        examService.createExam(request);
        return ResponseEntity.status(HttpStatus.OK).body("created successfully");
    }

    @GetMapping("/allExams")
    public ResponseEntity<List<ExamProjection>> getAllExams() throws ExamException {
        log.info("Controller: Request received to fetch all exam projections");
        return ResponseEntity.ok(examService.findAllExams());
    }

}
