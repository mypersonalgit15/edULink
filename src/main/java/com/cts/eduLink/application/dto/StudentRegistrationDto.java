package com.cts.eduLink.application.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class StudentRegistrationDto {

    @NotBlank(message = "Name is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String userEmail;

    @NotNull(message = "Phone number is required")
    @Column(unique = true, nullable = false)
    private Long phoneNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Birth date must be in the past")
    @Column(nullable = false)
    private LocalDate studentDOB;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other|Prefer not to say)$")
    @Column(nullable = false)
    private String studentGender;

    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String studentAddress;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
//            message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character")
    private String password;
}
