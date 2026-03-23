package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.classexception.GradeException;
import com.cts.eduLink.application.entity.Grade;
import com.cts.eduLink.application.repository.GradeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class GradeServiceImpl implements IGradeService{
    private final GradeRepository gradeRepository;

    @Override
    public String findGradeStatus(Long gradeId) throws GradeException {
        log.info("Fetching grade status for ID: {}", gradeId);
        Optional<Grade> grade = gradeRepository.findGradeById(gradeId);
        if(grade.isEmpty()){
            log.error("Grade lookup failed: No assignment found with ID {}", gradeId);
            throw new GradeException("NO assignment available with id: "+gradeId,HttpStatus.NOT_FOUND);
        }
        String status = gradeRepository.findGradeStatus(gradeId);
        log.debug("Successfully retrieved status '{}' for grade ID: {}", status, gradeId);
        return status;
    }

    @Override
    public double findTotalGradeByStudentId(Long studentId) throws CourseException {
        log.info("Calculating total grade for student ID: {}", studentId);
        Optional<Grade> grade = gradeRepository.checkStudentAvailableInGrade(studentId);
        if(grade.isEmpty()){
            log.warn("Grade calculation aborted: Student ID {} has no recorded tests.", studentId);
            throw new CourseException(studentId+" is not given any test yet!",HttpStatus.NOT_FOUND);
        }
        double totalGrade = gradeRepository.findGradeByStudentId(studentId);
        log.info("Total grade for student ID {}: {}", studentId, totalGrade);
        return totalGrade;
    }
}
