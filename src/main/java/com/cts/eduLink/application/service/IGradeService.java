package com.cts.eduLink.application.service;

public interface IGradeService {
    String findGradeStatus(Long gradeId);
    double findTotalGradeByStudentId(Long studentId);
}
