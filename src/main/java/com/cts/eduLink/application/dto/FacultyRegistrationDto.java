package com.cts.eduLink.application.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacultyRegistrationDto {

    private String userName;
    private String userEmail;
    private Long phoneNumber;
    private String facultyGender;
    private String studentAddress;
    private int facultyYearOfExperience;
}
