package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentStatusRepository extends JpaRepository<AssignmentStatus, Long> {
    List<AssignmentStatus> findByStudentIdAndCourseId(Long studentId, Long courseId);
}