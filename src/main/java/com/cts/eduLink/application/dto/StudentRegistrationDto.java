package com.cts.eduLink.application.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRegistrationDto {
    private String userName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @Column(nullable = false)
    private LocalDate studentDOB;

    @Column(nullable = false)
    private String studentGender;

    @Column(nullable = false)
    private String studentAddress;
}
