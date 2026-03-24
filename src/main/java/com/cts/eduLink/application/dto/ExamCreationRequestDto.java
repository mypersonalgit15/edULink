package com.cts.eduLink.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamCreationRequestDto {
    private String examName;
    private Long courseId;
    private String status;
    private int candidates;
}