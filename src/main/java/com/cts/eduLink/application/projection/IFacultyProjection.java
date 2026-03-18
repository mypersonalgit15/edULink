package com.cts.eduLink.application.projection;

public interface IFacultyProjection {
    // From AppUser (via the Join in your Repository)
    String getUserName();
    String getUserEmail();
    Long getPhoneNumber();

    // From Faculty
    String getFacultyGender();
    int getFacultyYearOfExperience();
    String getFacultyAddress();
    double getFacultyRating();
}