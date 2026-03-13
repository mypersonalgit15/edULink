package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.FacultyRegistrationDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.entity.Role;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.repository.RoleRepository;
import com.cts.eduLink.application.util.ClassSeparatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
