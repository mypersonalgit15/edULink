package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.FeedbackDto;
import com.cts.eduLink.application.projection.FeedbackProjection;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IFeedbackService {
    String registerFeedback(FeedbackDto feedbackDto);
    List<FeedbackProjection> findFeedBackList();
}
