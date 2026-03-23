package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.AppUserException;
import com.cts.eduLink.application.classexception.StudentException;
import com.cts.eduLink.application.dto.StudentRegistrationDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Role;
import com.cts.eduLink.application.entity.Student;
import com.cts.eduLink.application.repository.RoleRepository;
import com.cts.eduLink.application.repository.StudentRepository;
import com.cts.eduLink.application.util.DtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository ;
    private final RoleRepository roleRepository;
    private final IAppUserService iAppUserService;

    @Override
    @Transactional
    public String registerStudent(StudentRegistrationDto studentRegistrationDto) throws StudentException, AppUserException {
        log.info("Initiating student registration for user: {}", studentRegistrationDto.getUserEmail());
        log.debug("Extracting student and user entities from DTO");
        Student student = DtoMapper.studentDtoSeparator(studentRegistrationDto);
        AppUser appUser = DtoMapper.appUserDtoSeparator(studentRegistrationDto);
        log.info("Extraction completed for student and user entities from DTO");
        Optional<Role> role = roleRepository.findRoleByName("STUDENT");
        appUser.setRole(role.get());
        student.setAppUser(appUser);
        log.error("Attempting to register AppUser and save Student entity");
        iAppUserService.registerAppUser(appUser);
        studentRepository.save(student);
        log.info("Successfully registered student. Assigned Student ID: {}", student.getStudentId());
//        return "Thanks for Registration, Your User id "; // return for testing
        return "Thanks for Registration, Your User Id is: "+student.getStudentId(); // return for development
    }
}
