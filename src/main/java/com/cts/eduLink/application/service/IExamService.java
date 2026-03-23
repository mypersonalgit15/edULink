package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.projection.ExamProjection;
import java.util.List;
import java.util.Map;

public interface IExamService {

    String createExam(ExamCreationRequestDto examCreationRequestDto);
    List<ExamProjection> findAllExams();
    String updateExam(Long examId, ExamCreationRequestDto examCreationRequestDto);
    String patchExam(Long examId, Map<String, Object> updates);
    String deleteExam(Long examId);

}
