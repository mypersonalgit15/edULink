package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private Long facultyId;

    @Column(nullable = false)
    private String facultyGender;

    @Column(nullable = false)
    private String studentAddress;

    @Column(nullable = false)
    private int facultyYearOfExperience;

    @Column(nullable = false)
    private double facultyRating;

    @ManyToMany
    @JoinTable(
            name = "faculty_course_mapping",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courseSet = new HashSet<>();
}
