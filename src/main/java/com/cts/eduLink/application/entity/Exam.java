package com.cts.eduLink.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime examLocalDateTime;
    private String examName;
    private String examStatus;
    private int candidates;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "exam")
    private List<Grade> gradeList;
}