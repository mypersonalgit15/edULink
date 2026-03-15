package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.service.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    private final IFacultyService facultyService;

    @Autowired
    public FacultyController(IFacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> getFacultyDetails(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        return ResponseEntity.ok(faculty);
    }
}
