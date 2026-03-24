package com.cts.eduLink.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String userEmail;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max=16,message = "Password must be at least 8 characters long")
    private String userPassword;
}
