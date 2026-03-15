package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.entity.AssignmentStatus;
import com.cts.eduLink.application.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final IAssignmentService assignmentService;

    @Autowired
    public AssignmentController(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssignmentStatus>> getAssignments(@PathVariable Long courseId, @RequestParam Long studentId) {
        List<AssignmentStatus> assignments = assignmentService.getAssignmentsForStudent(courseId, studentId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping("/{assignmentId}/complete")
    public ResponseEntity<String> completeAssignment(@PathVariable Long assignmentId, @RequestParam Long studentId) {
        assignmentService.completeAssignment(assignmentId, studentId);
        return ResponseEntity.ok("Assignment Completed");
    }

    @GetMapping("/course/{courseId}/exam-access")
    public ResponseEntity<Boolean> canTakeExam(@PathVariable Long courseId, @RequestParam Long studentId) {
        boolean canTake = assignmentService.canStudentTakeExam(courseId, studentId);
        return ResponseEntity.ok(canTake);
    }
}
