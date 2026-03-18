package com.cts.eduLink.application.service;

import com.cts.eduLink.application.dto.ExamCreationRequestDto;
import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.entity.Course;
import com.cts.eduLink.application.repository.ExamRepository;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.classexception.ExamException;
import com.cts.eduLink.application.util.ClassSeparatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
//        BeanUtils.copyProperties(examCreationRequestDto,exam);
        Course course = courseRepository.findByCourseId(examCreationRequestDto.getCourseId())
                .orElseThrow(() -> new ExamException("Course not found with ID: " + examCreationRequestDto.getCourseId()));
        exam.setCourse(course);

        examRepository.save(exam);
        log.info("Exam created successfully: {}", examCreationRequestDto.getExamName());
        return "Exam created successfully with ID: " + exam.getId();
    }

    @Override
    public List<Exam> getAllExams() {
        log.info("Fetching all exams");
        return examRepository.findAll();
    }

//    @Override
//    public List<Exam> getUpcomingExams() {
//        log.info("Fetching upcoming exams");
//        LocalDateTime now = LocalDateTime.now();
//        return examRepository.findAll().stream()
//                .filter(exam -> exam.getExamLocalDateTime() != null && exam.getExamLocalDateTime().isAfter(now))
//                .collect(Collectors.toList());
//    }
}
