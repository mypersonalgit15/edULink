package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService implements IExamService {

    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam getExamByCourseId(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }

    @Override
    public void submitExam(Long examId, Long studentId, List<String> answers) {
        // Logic to process answers, calculate score, etc.
        // For now, just mark as completed
    }
}