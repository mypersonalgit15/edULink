package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.AppUserException;
import com.cts.eduLink.application.classexception.FeedbackException;
import com.cts.eduLink.application.dto.FeedbackDto;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.FeedBack;
import com.cts.eduLink.application.repository.FacultyRepository;
import com.cts.eduLink.application.repository.FeedBackRepository;
import com.cts.eduLink.application.repository.StudentRepository;
import com.cts.eduLink.application.util.DtoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedBackRepository feedBackRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public String registerFeedback(FeedbackDto feedbackDto) throws AppUserException, FeedbackException {
        log.info("Attempting to register feedback for User ID: {} with Reviewer Type: {}",
                feedbackDto.getUserId(), feedbackDto.getReviewerType());
        Optional<AppUser> appUser = Optional.empty();
        if(feedbackDto.getReviewerType().equalsIgnoreCase("STUDENT")){
            log.debug("Fetching AppUser from student repository for ID: {}", feedbackDto.getUserId());
            appUser = studentRepository.findAppUserByStudentId(feedbackDto.getUserId());
        } else if(feedbackDto.getReviewerType().equalsIgnoreCase("FACULTY")){
            log.debug("Fetching AppUser from faculty repository for ID: {}", feedbackDto.getUserId());
            appUser = facultyRepository.findAppUserByFacultyId(feedbackDto.getUserId());
        }
        if (appUser.isEmpty() || appUser.isEmpty()){
            log.error("Feedback registration failed: No AppUser found for ID: {} and Type: {}",
                    feedbackDto.getUserId(), feedbackDto.getReviewerType());
            throw new AppUserException("Invalid feedback type", HttpStatus.BAD_REQUEST);
        }
        FeedBack feedBack = DtoMapper.feedBackDtoSeparator(feedbackDto);
        feedBack.setAppUser(appUser.get());
        feedBackRepository.save(feedBack);
        log.info("Feedback successfully saved for User ID: {}", feedbackDto.getUserId());
        return "Thank you for your feedback!";
    }
}
