package com.cts.eduLink.application.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Setter
@Getter
public class FacultyRegistrationDto {

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String userEmail;

    @NotNull(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be 10 digits")
    private Long phoneNumber;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String facultyGender;

    @NotBlank(message = "Address is required")
    private String facultyAddress;

    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 60, message = "Experience seems unrealistic")
    private int facultyYearOfExperience;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
//            message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character")
        private String password;
}
