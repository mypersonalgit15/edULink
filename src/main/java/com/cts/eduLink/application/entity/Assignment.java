package com.cts.eduLink.application.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long assignmentId;

    private String assignmentTitle;
    private String assignmentDescription;
    private LocalDateTime assignmentCreatedDate;
    private LocalDateTime assignmentDueDate;
    private String assignmentStatus;
    private int totalMarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<AssignmentStatus> assignmentStatusList;
}
