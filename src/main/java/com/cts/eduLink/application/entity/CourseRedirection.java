package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseRedirection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String redirectFrom;

    @Column(nullable = false)
    private String redirectTo;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}