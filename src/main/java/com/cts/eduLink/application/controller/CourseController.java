package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.CourseEnrollmentDto;
import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.projection.CourseDetailByIdProjection;
import com.cts.eduLink.application.projection.CourseDetailProjection;
import com.cts.eduLink.application.service.ICourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@Slf4j
public class CourseController {

    private final ICourseService iCourseService;


    @PatchMapping("/updateRating/{courseId}/{newCourseRating}")
    public ResponseEntity<String> updateCourseRating(@PathVariable Long courseId, @PathVariable double newCourseRating){

        log.info("Received PATCH request: Updating rating for courseId: {} to {}", courseId, newCourseRating);
        return ResponseEntity.status(200).body(iCourseService.updateCourseRating(courseId,newCourseRating));
    }
}
