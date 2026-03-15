package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.AssignmentStatus;
import java.util.List;

public interface IAssignmentService {
    List<AssignmentStatus> getAssignmentsForStudent(Long courseId, Long studentId);
    void completeAssignment(Long assignmentId, Long studentId);
    boolean canStudentTakeExam(Long courseId, Long studentId);
}
