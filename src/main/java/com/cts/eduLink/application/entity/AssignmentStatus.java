package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AssignmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // e.g., "Pending", "Submitted", "Graded"

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Perhaps link to specific assignment, but since no Assignment entity, maybe this is it
}