package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.LearningMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Long> {
}