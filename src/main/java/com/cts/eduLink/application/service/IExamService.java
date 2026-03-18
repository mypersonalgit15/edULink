package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.entity.Exam;
import java.time.LocalDateTime;
import java.util.List;

public interface IExamService {

    // Method to create a new exam
    String createExam(ExamCreationRequestDto examCreationRequestDto);

    // Method to fetch all exams
    List<Exam> getAllExams();

    // Method to fetch upcoming exams
//    List<Exam> getUpcomingExams();
}
