package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.FacultyException;
import com.cts.eduLink.application.dto.FacultyDashboardDto;
import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.entity.*;
import com.cts.eduLink.application.projection.FacultyDetailProjection;
import com.cts.eduLink.application.projection.FacultyProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.repository.RoleRepository;
import com.cts.eduLink.application.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor
@Slf4j
public class FacultyServiceImpl implements IFacultyService{
    private final FacultyRepository facultyRepository;
    private final AppUserServiceImpl appUserService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    @Transactional
    @Override
    public String registerFaculty(FacultyRegistrationDto facultyRegistrationDto) {

        log.debug("AppUser and Faculty separation initiated");
        AppUser appUser = DtoMapper.appUserDtoSeparator(facultyRegistrationDto,passwordEncoder);
        Faculty faculty = DtoMapper.facultyDtoSeparator(facultyRegistrationDto);
        Optional<Role> role = roleRepository.findRoleByName("FACULTY");

        appUser.setRole(role.get());
        log.debug("appUser instance has sent for registration");
        appUserService.registerAppUser(appUser);
        faculty.setAppUser(appUser);
        facultyRepository.save(faculty);
        log.info("faculty entity has saved into database for {}",appUser.getUserEmail());
        return "You have registered SuccessFully, your login id is: "+faculty.getFacultyId();
    }

    @Override
    public FacultyDashboardDto getFacultyDashboard(Long facultyId) {

        log.info("Generating dashboard data for faculty: {}", facultyId);

        // 1. Get the course count from CourseRepository
        int courses = courseRepository.getFacultyCourseCount(facultyId);

        // 2. Get the exam count from FacultyRepository (or ExamRepository)
        int exams = facultyRepository.getUpcomingExamsCount(facultyId);

        // 3. Combine them into the DTO
        FacultyDashboardDto dashboard = new FacultyDashboardDto();
        dashboard.setCourseCount(courses);
        dashboard.setUpcomingExamsCount(exams);

        return dashboard;
    }

    @Override
    public List<Course> getFacultyCourses(Long facultyId) {
        log.debug("Fetching courses for faculty: {}", facultyId);
        Optional<Faculty> facultyOptional = facultyRepository.findFacultyById(facultyId);

        if (facultyOptional.isEmpty()) {
            log.warn("Faculty not found with id: {}", facultyId);
            return List.of();
        }

        Faculty faculty = facultyOptional.get();
        List<Course> courses = new ArrayList<>(faculty.getCourseSet());
        log.debug("Found {} courses for faculty: {}", courses.size(), facultyId);
        return courses;
    }

    @Override
    public String updateFaculty(Long facultyId, FacultyRegistrationDto facultyRegistrationDto) {
        return "";
    }

    @Override
    public String patchFaculty(Long facultyId, Map<String, Object> updates) {
        return "";
    }

    @Override
    public String deleteFaculty(Long facultyId) {
        return "";
    }

    public List<Exam> getupComingExams(Long facultyId ){
        return facultyRepository.findUpcomingExamsByFacultyId(facultyId);
    }

    public int getupComingExamsCount(Long facultyId){
        return facultyRepository.getUpcomingExamsCount(facultyId);
    }

    @Override
    public List<FacultyDetailProjection> filterFacultyByRating(int facultyRating) {
        return List.of();
    }

    @Override
    public String updateFacultyRating(Long facultyId, double newFacultyRating) {
        return "";
    }


    //    @Override
//    public Long getTotalStudents(Long facultyId) {
//        log.debug("Fetching total students for faculty: {}", facultyId);
//        Optional<Faculty> facultyOptional = facultyRepository.findFacultyById(facultyId);
//
//        if (facultyOptional.isEmpty()) {
//            log.warn("Faculty not found with id: {}", facultyId);
//            return 0L;
//        }
//
//        Faculty faculty = facultyOptional.get();
//
//        // Get all unique students from faculty's courses
//        Set<Student> uniqueStudents = faculty.getCourseSet().stream()
//                .flatMap(course -> course.getStudentSet().stream())
//                .collect(Collectors.toSet());
//
//        long totalStudents = uniqueStudents.size();
//        log.debug("Found {} total students for faculty: {}", totalStudents, facultyId);
//        return totalStudents;
//    }
    public Optional<FacultyProjection> getFacultyProfile(Long facultyId) throws FacultyException {
        return facultyRepository.findFacultyProfile(facultyId);
    }

}

