package com.cts.eduLink.application.util;

import com.cts.eduLink.application.dto.CourseRegistrationDto;
import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.StudentRegistrationDto;
import com.cts.eduLink.application.entity.*;

import java.time.LocalDateTime;

public class ClassSeparatorUtils {

    public static Student studentDtoSeparator(StudentRegistrationDto studentDto){
        Student student = new Student();
        student.setStudentAddress(studentDto.getStudentAddress());
        student.setStudentDOB(studentDto.getStudentDOB());
        student.setStudentGender(studentDto.getStudentGender());
        student.setStudentEnrollmentDateTime(LocalDateTime.now());
        Long studentId = UIDGeneratorUtils.uidGenerator();
        student.setStudentId(studentId);
        return student;
    }
    public static AppUser appUserDtoSeparator(StudentRegistrationDto appUserDto){
        AppUser appUser = new AppUser();
        appUser.setUserEmail(appUserDto.getUserEmail());
        appUser.setUserName(appUserDto.getUserName());
        appUser.setPhoneNumber(appUserDto.getPhoneNumber());
        return appUser;
    }

    public static Course facultyDtoSeparator(CourseRegistrationDto courseRegistrationDto){
        Course course = new Course();
        course.setCourseTitle(courseRegistrationDto.getCourseTitle());
        course.setCourseSubject(courseRegistrationDto.getCourseSubject());
        course.setCourseCredit(courseRegistrationDto.getCourseCredit());
        course.setCourseRating(0.0);
        course.setCourseGradeLevel(courseRegistrationDto.getCourseGradeLevel());
        Long courseId = UIDGeneratorUtils.uidGenerator();
        course.setCourseId(courseId);
        return course;
    }

    public static void updateCourseFromDto(Course course, CourseRegistrationDto dto) {
        course.setCourseTitle(dto.getCourseTitle());
        course.setCourseSubject(dto.getCourseSubject());
        course.setCourseCredit(dto.getCourseCredit());
        course.setCourseGradeLevel(dto.getCourseGradeLevel());
        course.setCourseStatus(dto.getCourseStatus());
    }

    public static Faculty facultyDtoSeparator(FacultyRegistrationDto facultyRegistrationDto){
        Faculty faculty = new Faculty();
        faculty.setFacultyGender(facultyRegistrationDto.getFacultyGender());
        faculty.setFacultyYearOfExperience(facultyRegistrationDto.getFacultyYearOfExperience());
        faculty.setStudentAddress(facultyRegistrationDto.getStudentAddress());
        faculty.setFacultyRating(0.0);
        Long facultyId = UIDGeneratorUtils.uidGenerator();
        faculty.setFacultyId(facultyId);
        return faculty;
    }

    public static void updateFacultyFromDto(Faculty faculty, FacultyRegistrationDto dto) {
        // Update Faculty-specific fields
        faculty.setFacultyGender(dto.getFacultyGender());
        faculty.setFacultyYearOfExperience(dto.getFacultyYearOfExperience());
        faculty.setStudentAddress(dto.getStudentAddress());

        // Update associated AppUser fields
        if (faculty.getAppUser() != null) {
            faculty.getAppUser().setUserName(dto.getUserName());
            faculty.getAppUser().setUserEmail(dto.getUserEmail());
            faculty.getAppUser().setPhoneNumber(dto.getPhoneNumber());
        }
    }

    public static AppUser appUserDtoSeparator(FacultyRegistrationDto facultyRegistrationDto){
        AppUser appUser = new AppUser();
        appUser.setUserName(facultyRegistrationDto.getUserName());
        appUser.setUserEmail(facultyRegistrationDto.getUserEmail());
        appUser.setPhoneNumber(facultyRegistrationDto.getPhoneNumber());
        return appUser;
    }
    public static Exam ExamDtoSeperator(ExamCreationRequestDto examCreationRequestDto){
        Exam exam = new Exam();
        exam.setExamName(examCreationRequestDto.getExamName());
        exam.setExamLocalDateTime(LocalDateTime.now());
        exam.setExamStatus(examCreationRequestDto.getStatus());
        exam.setCandidates(examCreationRequestDto.getCandidates());

        return exam;

    }


}
