package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Query("select a from Assignment a where a.assignmentId = :assignmentId")
    Optional<Assignment> findAssignmentById(@Param("assignmentId") Long assignmentId);

    @Query("select a from Assignment a where a.course.id = :courseId")
    List<Assignment> findAssignmentsByCourseId(@Param("courseId") Long courseId);

    @Query("select a from Assignment a where a.assignmentStatus = :status")
    List<Assignment> findAssignmentsByStatus(@Param("status") String status);
}
