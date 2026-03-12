package com.cts.eduLink.application.controller;

import com.cts.eduLink.application.dto.StudentRegistrationDto;
import com.cts.eduLink.application.service.IStudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
@Slf4j
public class StudentController {

    private final IStudentService iStudentService;

    @PostMapping("/register")
    public ResponseEntity<String> studentRegistration(@RequestBody StudentRegistrationDto studentRegistrationDto){
        log.info("Student's registration request has been initiated successFully by {}",studentRegistrationDto.getUserName());
        return ResponseEntity.status(200).body(iStudentService.registerStudent(studentRegistrationDto));

    }

}
