package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.FacultyDashboardDto;
import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.projection.IFacultyProjection;
import com.cts.eduLink.application.service.FacultyServiceImpl;
import com.cts.eduLink.application.service.IFacultyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")
@AllArgsConstructor
@Slf4j
public class FacultyController {

    private final IFacultyService facultyService;

    @PostMapping("/register")
    public ResponseEntity<String> registerFaculty(@RequestBody FacultyRegistrationDto facultyRegistrationDto){
        log.info("{} has initiated the registration as a Faculty",facultyRegistrationDto.getUserEmail());
        return ResponseEntity.status(200).body(facultyService.registerFaculty(facultyRegistrationDto));
    }

    @PutMapping("/update/{facultyId}")
    public ResponseEntity<String> updateFaculty(@PathVariable Long facultyId, @RequestBody FacultyRegistrationDto facultyRegistrationDto) {
        log.info("Received request to update faculty with ID: {}", facultyId);
        String response = facultyService.updateFaculty(facultyId, facultyRegistrationDto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/patch/{facultyId}")
    public ResponseEntity<String> patchFaculty(
            @PathVariable Long facultyId,
            @RequestBody Map<String, Object> updates) {

        log.info("Received patch request for facultyId: {}", facultyId);
        String response = facultyService.patchFaculty(facultyId, updates);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{facultyId}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long facultyId) {
        log.info("Received request to delete faculty with ID: {}", facultyId);
        String response = facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/{facultyId}")
    public ResponseEntity<FacultyDashboardDto> getFacultyDashboard(@PathVariable Long facultyId){
        log.info("Faculty dashboard requested for facultyId: {}", facultyId);
        FacultyDashboardDto dashboardDto = facultyService.getFacultyDashboard(facultyId);
        return ResponseEntity.status(200).body(dashboardDto);
    }

    @GetMapping("/profile/{facultyId}")
    public ResponseEntity<IFacultyProjection> getFacultyProfile(@PathVariable Long facultyId){
        return facultyService.getFacultyProfile(facultyId)
                .map(app -> ResponseEntity.ok(app))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/upcoming/{facultyId}")
    public ResponseEntity<List<Exam>> getupComingExams(@PathVariable Long facultyId) {
        // This now matches the return type of your service/repository
        return ResponseEntity.ok(facultyService.getupComingExams(facultyId));
    }

    @GetMapping("/upComingCount/{facultyId}")
    public Map<String,Integer> getupComingExamsCount(@PathVariable Long facultyId){
        int count = facultyService.getupComingExamsCount(facultyId);
        return Map.of("upComing Exams", count);
    }



}
