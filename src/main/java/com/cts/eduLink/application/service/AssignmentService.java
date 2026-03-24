package com.cts.eduLink.application.service;

import com.cts.eduLink.application.entity.AssignmentStatus;
import com.cts.eduLink.application.repository.AssignmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService implements IAssignmentService {

    private final AssignmentStatusRepository assignmentStatusRepository;

    @Autowired
    public AssignmentService(AssignmentStatusRepository assignmentStatusRepository) {
        this.assignmentStatusRepository = assignmentStatusRepository;
    }

    @Override
    public List<AssignmentStatus> getAssignmentsForStudent(Long courseId, Long studentId) {
        return assignmentStatusRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public void completeAssignment(Long assignmentId, Long studentId) {
        AssignmentStatus assignment = assignmentStatusRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignment.setStatus("Completed");
        assignmentStatusRepository.save(assignment);
    }

    @Override
    public boolean canStudentTakeExam(Long courseId, Long studentId) {
        List<AssignmentStatus> assignments = getAssignmentsForStudent(courseId, studentId);
        return assignments.stream().allMatch(a -> "Completed".equals(a.getStatus()));
    }
}