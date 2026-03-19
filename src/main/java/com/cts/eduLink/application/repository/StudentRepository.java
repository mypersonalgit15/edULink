package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentById(Long studentId);
}
