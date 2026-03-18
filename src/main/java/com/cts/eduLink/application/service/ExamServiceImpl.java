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
    @org.springframework.transaction.annotation.Transactional
    public String updateExam(Long examId, ExamCreationRequestDto dto) {
        log.info("Updating exam details for ID: {}", examId);

        // 1. Find existing exam
        Exam existingExam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamException("Exam not found with ID: " + examId, HttpStatus.NOT_FOUND));

        // 2. Update basic fields using utility
        ClassSeparatorUtils.updateExamFromDto(existingExam, dto);

        // 3. Update the associated course if a new courseId is provided
        if (dto.getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourseId())
                    .orElseThrow(() -> new ExamException("Course not found with ID: " + dto.getCourseId(), HttpStatus.NOT_FOUND));
            existingExam.setCourse(course);
        }

        // 4. Save the updated entity
        examRepository.save(existingExam);

        log.info("Exam ID: {} updated successfully", examId);
        return "Exam updated successfully!";
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public String patchExam(Long examId, java.util.Map<String, Object> updates) {
        log.info("Patch update initiated for Exam ID: {}", examId);

        // 1. Find the existing exam
        com.cts.eduLink.application.entity.Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new com.cts.eduLink.application.classexception.ExamException(
                        "Exam not found with ID: " + examId,
                        org.springframework.http.HttpStatus.NOT_FOUND
                ));

        // 2. Iterate through the map and update only provided fields
        updates.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "examName":
                        exam.setExamName(value.toString());
                        break;
                    case "status":
                        exam.setExamStatus(value.toString());
                        break;
                    case "candidates":
                        exam.setCandidates(Integer.parseInt(value.toString()));
                        break;
                    case "courseId":
                        Long newCourseId = Long.valueOf(value.toString());
                        com.cts.eduLink.application.entity.Course course = courseRepository.findByCourseId(newCourseId)
                                .orElseThrow(() -> new com.cts.eduLink.application.classexception.ExamException(
                                        "Course not found with ID: " + newCourseId,
                                        org.springframework.http.HttpStatus.NOT_FOUND
                                ));
                        exam.setCourse(course);
                        break;
                }
            }
        });

        // 3. Save the partially updated entity
        examRepository.save(exam);
        log.info("Exam ID: {} partially updated successfully", examId);
        return "Exam partially updated successfully!";
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public String deleteExam(Long examId) {
        log.info("Deletion request initiated for Exam ID: {}", examId);

        // 1. Find the existing exam using the database primary key ID
        com.cts.eduLink.application.entity.Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new com.cts.eduLink.application.classexception.ExamException(
                        "Exam not found with ID: " + examId,
                        org.springframework.http.HttpStatus.NOT_FOUND
                ));

        // 2. Delete the exam
        examRepository.delete(exam);

        log.info("Exam ID: {} deleted successfully", examId);
        return "Exam deleted successfully!";
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
