package com.cts.eduLink.application.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor; // Good practice to have this too

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackProjection {
    private String userName;
    private String message;
    private Double rating; // Changed from double to Double
}