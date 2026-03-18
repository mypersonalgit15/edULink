package com.cts.eduLink.application.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExamProjection {
    private String examName;
    private LocalDateTime examLocalDateTime;
    private String examStatus;
    private int candidates;
}