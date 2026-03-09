package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.Exam;
import java.util.List;

public interface IExamService {
    Exam getExamByCourseId(Long courseId);
    void submitExam(Long examId, Long studentId, List<String> answers);
}
