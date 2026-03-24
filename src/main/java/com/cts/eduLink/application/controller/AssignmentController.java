package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.entity.AssignmentStatus;
import com.cts.eduLink.application.service.IAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    private final IAssignmentService assignmentService;

    @Autowired
    public AssignmentController(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssignmentStatus>> getAssignments(@Valid @PathVariable Long courseId, @RequestParam Long studentId) {
        List<AssignmentStatus> assignments = assignmentService.getAssignmentsForStudent(courseId, studentId);
        return ResponseEntity.ok(assignments);
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/{assignmentId}/complete")
    public ResponseEntity<String> completeAssignment(@Valid @PathVariable Long assignmentId, @RequestParam Long studentId) {
        assignmentService.completeAssignment(assignmentId, studentId);
        return ResponseEntity.ok("Assignment Completed");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/course/{courseId}/exam-access")
    public ResponseEntity<Boolean> canTakeExam(@Valid @PathVariable Long courseId, @RequestParam Long studentId) {
        boolean canTake = assignmentService.canStudentTakeExam(courseId, studentId);
        return ResponseEntity.ok(canTake);
    }
}
