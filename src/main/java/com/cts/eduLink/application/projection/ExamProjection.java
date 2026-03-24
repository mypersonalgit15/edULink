package com.cts.eduLink.application.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ExamProjection {
    private String examName;
    private LocalDateTime examLocalDateTime;
    private String examStatus;
    private int candidates;
}