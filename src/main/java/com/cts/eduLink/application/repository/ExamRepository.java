package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    // Add custom query methods if needed
}