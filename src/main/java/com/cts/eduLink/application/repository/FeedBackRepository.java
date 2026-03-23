package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
}
