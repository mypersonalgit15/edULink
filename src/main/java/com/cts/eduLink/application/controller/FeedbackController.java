package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.FeedbackDto;
import com.cts.eduLink.application.projection.FeedbackProjection;
import com.cts.eduLink.application.service.FeedbackService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;


    @GetMapping("/getFeedbackList")
    private  ResponseEntity<List<FeedbackProjection>> findFeedBackList(){
        return ResponseEntity.status(200).body(feedbackService.findFeedBackList());
    }

}
