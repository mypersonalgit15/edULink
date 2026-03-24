package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.FacultyDashboardDto;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.projection.FacultyProjection;
import com.cts.eduLink.application.projection.FacultyDetailProjection;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFacultyService {
    String registerFaculty(FacultyRegistrationDto facultyRegistrationDto);
    FacultyDashboardDto getFacultyDashboard(Long facultyId);
    List<Course> getFacultyCourses(Long facultyId);
    String updateFaculty(Long facultyId, FacultyRegistrationDto facultyRegistrationDto);
    String patchFaculty(Long facultyId, Map<String, Object> updates);
    String deleteFaculty(Long facultyId);

    Optional<FacultyProjection> getFacultyProfile(Long facultyId);
    List<Exam> getupComingExams(Long facultyId );
    public  int getupComingExamsCount(Long facultyId);
    List<FacultyDetailProjection> filterFacultyByRating(@Param("facultyRating") int facultyRating);
    String updateFacultyRating(Long facultyId, double newFacultyRating);
}
