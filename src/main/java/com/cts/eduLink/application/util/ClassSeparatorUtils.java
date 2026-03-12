package com.cts.eduLink.application.util;

import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.dto.StudentRegistrationDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.entity.Student;

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
    public static Faculty facultyDtoSeparator(FacultyRegistrationDto facultyRegistrationDto){
        Faculty faculty = new Faculty();
        faculty.setFacultyGender(facultyRegistrationDto.getFacultyGender());
        faculty.setFacultyYearOfExperience(facultyRegistrationDto.getFacultyYearOfExperience());
        faculty.setStudentAddress(facultyRegistrationDto.getStudentAddress());
        Long facultyId = UIDGeneratorUtils.uidGenerator();
        faculty.setFacultyId(facultyId);
        return faculty;
    }
    public static AppUser appUserDtoSeparator(FacultyRegistrationDto facultyRegistrationDto){
        AppUser appUser = new AppUser();
        appUser.setUserName(facultyRegistrationDto.getUserName());
        appUser.setUserEmail(facultyRegistrationDto.getUserEmail());
        appUser.setPhoneNumber(facultyRegistrationDto.getPhoneNumber());
        return appUser;
    }
}
