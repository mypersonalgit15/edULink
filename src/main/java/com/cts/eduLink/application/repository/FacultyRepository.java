package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}