package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.projection.FacultyDetailProjection;
import com.cts.eduLink.application.service.IFacultyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@AllArgsConstructor
@Slf4j
public class FacultyController {

    private final IFacultyService facultyService;

    @PatchMapping("/updateRating/{facultyId}/{newFacultyRating}")
    public ResponseEntity<String> updateFacultyRating(@PathVariable Long facultyId, @PathVariable double newFacultyRating){
        return ResponseEntity.status(200).body(facultyService.updateFacultyRating(facultyId,newFacultyRating));
    }

}
