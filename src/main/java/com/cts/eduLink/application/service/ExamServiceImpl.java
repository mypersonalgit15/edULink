package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.projection.ExamProjection;
import com.cts.eduLink.application.repository.ExamRepository;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.classexception.ExamException;
import com.cts.eduLink.application.util.ClassSeparatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ExamServiceImpl implements IExamService {

@Autowired
CourseRepository courseRepository;

@Autowired
ExamRepository examRepository;


    public String createExam(ExamCreationRequestDto examCreationRequestDto) {
        log.info("Creating a new exam: {}", examCreationRequestDto.getExamName());

        Exam exam = ClassSeparatorUtils.ExamDtoSeperator(examCreationRequestDto);

        // Pass both the message and the HttpStatus to match your ExamException constructor
        Course course = courseRepository.findByCourseId(examCreationRequestDto.getCourseId())
                .orElseThrow(() -> new ExamException(
                        "Course not found with ID: " + examCreationRequestDto.getCourseId(),
                        HttpStatus.NOT_FOUND
                ));

        exam.setCourse(course);

        examRepository.save(exam);
        log.info("Exam created successfully: {}", examCreationRequestDto.getExamName());
        return "Exam created successfully with ID: " + exam.getId();
    }

    @Override
    public List<ExamProjection> findAllExams() throws ExamException {
        log.info("User has requested to display all exams via projection");

        // Fetch the projected data from the repository
        List<ExamProjection> examProjections = examRepository.findAllExams();

        // Check if the list is empty and throw a custom exception if it is
        if (examProjections.isEmpty()) {
            log.error("No exams are currently available in the system");
            throw new ExamException("No exams found!", HttpStatus.NOT_FOUND);
        }

        // Log success and details of the first record for debugging
        log.info("Exam list accessed successfully. Total exams found: {}. First exam: {}",
                examProjections.size(),
                examProjections.get(0).getExamName());

        return examProjections;
    }

}
