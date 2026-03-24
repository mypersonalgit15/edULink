package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.FacultyException;
import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.FacultyDashboardDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.entity.Role;
import com.cts.eduLink.application.projection.FacultyProjection;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.projection.FacultyDetailProjection;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import java.util.Map;
import com.cts.eduLink.application.util.DtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cts.eduLink.application.constants.ErrorConstant.Faculty_Error;

import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.entity.Course;

@Service
@AllArgsConstructor
@Slf4j
public class FacultyServiceImpl implements IFacultyService {

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
    @org.springframework.transaction.annotation.Transactional
    public String updateFaculty(Long facultyId, FacultyRegistrationDto dto) {
        log.info("Updating faculty profile for ID: {}", facultyId);

        // Find existing faculty or throw exception
        Faculty existingFaculty = facultyRepository.findFacultyById(facultyId)
                .orElseThrow(() -> new FacultyException("Faculty not found with ID: " + facultyId, org.springframework.http.HttpStatus.NOT_FOUND));

        // Use utility to map DTO fields to existing entity
        DtoMapper.updateFacultyFromDto(existingFaculty, dto);

        // Save updated entity (JPA identifies this as an update because the ID is present)
        facultyRepository.save(existingFaculty);

        log.info("Faculty profile updated successfully for ID: {}", facultyId);
        return "Faculty profile updated successfully!";
    }

    @Override
    public List<FacultyDetailProjection> filterFacultyByRating(int facultyRating) throws FacultyException {
        log.info("Faculty rating filtration request has sent to database");
        List<FacultyDetailProjection> facultyDetailProjections = facultyRepository.filterFacultyByRating(facultyRating);
        if (facultyDetailProjections.isEmpty()) {
            log.error("No faculty available with {} ratting", facultyRating);
            throw new FacultyException(Faculty_Error + facultyRating, HttpStatus.NOT_FOUND);
        }
        log.info("Faculty with rating {} fetch successfully and first faculty name is {}", facultyRating, facultyDetailProjections.getFirst().getFacultyName());
        return facultyDetailProjections;
    }
    @Transactional
    public String patchFaculty(Long facultyId, Map<String, Object> updates) {
        log.info("Patch update initiated for Faculty ID: {}", facultyId);

        Faculty faculty = facultyRepository.findFacultyById(facultyId)
                .orElseThrow(() -> new FacultyException("Faculty not found", HttpStatus.NOT_FOUND));

        updates.forEach((key, value) -> {
            switch (key) {
                case "phoneNumber":
                    faculty.getAppUser().setPhoneNumber(Long.valueOf(value.toString()));
                    break;
                case "facultyYearOfExperience":
                    faculty.setFacultyYearOfExperience((Integer) value);
                    break;
                case "studentAddress":
                    faculty.setFacultyAddress((String) value);
                    break;
                // Add other fields as needed
            }
        });

        facultyRepository.save(faculty);
        return "Faculty record partially updated successfully!";
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public String deleteFaculty(Long facultyId) {
        log.info("Deletion request initiated for Faculty ID: {}", facultyId);

        // Check if faculty exists using your existing repository method
        Faculty faculty = facultyRepository.findFacultyById(facultyId)
                .orElseThrow(() -> new FacultyException("Faculty not found with ID: " + facultyId, org.springframework.http.HttpStatus.NOT_FOUND));

        // Delete the entity
        facultyRepository.delete(faculty);

        log.info("Faculty ID: {} and associated user deleted successfully", facultyId);
        return "Faculty record deleted successfully!";
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

    public List<Exam> getupComingExams(Long facultyId ){
        return facultyRepository.findUpcomingExamsByFacultyId(facultyId);
    }

    public int getupComingExamsCount(Long facultyId){
        return facultyRepository.getUpcomingExamsCount(facultyId);
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

