package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.FacultyException;
import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.FacultyDashboardDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.entity.Role;
import com.cts.eduLink.application.projection.IFacultyProjection;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.repository.RoleRepository;
import com.cts.eduLink.application.util.ClassSeparatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.entity.Student;

@Service
@AllArgsConstructor
@Slf4j
public class FacultyServiceImpl implements IFacultyService {

    private final FacultyRepository facultyRepository;
    private final AppUserServiceImpl appUserService;
    private final RoleRepository roleRepository;

    @Override
    public String registerFaculty(FacultyRegistrationDto facultyRegistrationDto) {

        log.debug("AppUser and Faculty separation initiated");
        AppUser appUser = ClassSeparatorUtils.appUserDtoSeparator(facultyRegistrationDto);
        Faculty faculty = ClassSeparatorUtils.facultyDtoSeparator(facultyRegistrationDto);
        Optional<Role> role = roleRepository.findRoleByName("FACULTY");
        appUser.setRole(role.get());
        log.debug("appUser instance has sent for registration");
        appUserService.registerAppUser(appUser);
        faculty.setAppUser(appUser);
        facultyRepository.save(faculty);
        log.info("faculty entity has saved into database for "+appUser.getUserEmail());
        return "You have registered SuccessFully, your login id is: "+faculty.getFacultyId();
    }

    @Override
    public FacultyDashboardDto getFacultyDashboard(Long facultyId) {
        log.info("Fetching dashboard data for faculty with id: {}", facultyId);
        Optional<Faculty> facultyOptional = facultyRepository.findFacultyById(facultyId);
        
        if (facultyOptional.isEmpty()) {
            log.warn("Faculty not found with id: {}", facultyId);
            return new FacultyDashboardDto();
        }
        
        // Get faculty's courses
        List<Course> myCourses = getFacultyCourses(facultyId);
        
        // Get upcoming exams for faculty's courses
//        List<Exam> upcomingExamsList = getUpcomingExams(facultyId);
        
        // Get total students in faculty's courses
        Long totalStudents = getTotalStudents(facultyId);
        
        FacultyDashboardDto dashboardDto = new FacultyDashboardDto();
        dashboardDto.setTotalStudents(totalStudents);
        dashboardDto.setTotalCourses((long) myCourses.size());
//        dashboardDto.setUpcomingExams((long) upcomingExamsList.size());
//        dashboardDto.setMyCourses(myCourses);
//        dashboardDto.setUpcomingExamsList(upcomingExamsList);
        
        log.info("Dashboard data fetched successfully for faculty: {}", facultyId);
        return dashboardDto;
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

    public List<Exam> getupComingExams(Long facultyId ){
        return facultyRepository.findUpcomingExamsByFacultyId(facultyId);
    }

    public int getupComingExamsCount(Long facultyId){
        return facultyRepository.getUpcomingExamsCount(facultyId);
    }

//    @Override
//    public List<Exam> getUpcomingExams(Long facultyId) {
//        log.debug("Fetching upcoming exams for faculty: {}", facultyId);
//        Optional<Faculty> facultyOptional = facultyRepository.findFacultyById(facultyId);
//
//        if (facultyOptional.isEmpty()) {
//            log.warn("Faculty not found with id: {}", facultyId);
//            return List.of();
//        }
//
//        Faculty faculty = facultyOptional.get();
//        LocalDateTime now = LocalDateTime.now();
//
//         Get all exams for faculty's courses and filter upcoming ones
//        List<Exam> upcomingExams = faculty.getCourseSet().stream()
//                .flatMap(course -> course.getExamSet().stream())
//                .filter(exam -> exam.getExamLocalDateTime() != null && exam.getExamLocalDateTime().isAfter(now))
//                .collect(Collectors.toList());
//
//        log.debug("Found {} upcoming exams for faculty: {}", upcomingExams.size(), facultyId);
//        return upcomingExams;
//    }

    @Override
    public Long getTotalStudents(Long facultyId) {
        log.debug("Fetching total students for faculty: {}", facultyId);
        Optional<Faculty> facultyOptional = facultyRepository.findFacultyById(facultyId);
        
        if (facultyOptional.isEmpty()) {
            log.warn("Faculty not found with id: {}", facultyId);
            return 0L;
        }
        
        Faculty faculty = facultyOptional.get();
        
        // Get all unique students from faculty's courses
        Set<Student> uniqueStudents = faculty.getCourseSet().stream()
                .flatMap(course -> course.getStudentSet().stream())
                .collect(Collectors.toSet());
        
        long totalStudents = uniqueStudents.size();
        log.debug("Found {} total students for faculty: {}", totalStudents, facultyId);
        return totalStudents;
    }
    public Optional<IFacultyProjection> getFacultyProfile(Long facultyId) throws FacultyException {
        return facultyRepository.findFacultyProfile(facultyId);
    }

}

