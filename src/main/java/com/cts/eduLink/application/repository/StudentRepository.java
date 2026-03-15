package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}