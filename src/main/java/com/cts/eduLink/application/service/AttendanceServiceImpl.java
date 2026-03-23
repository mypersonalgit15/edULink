package com.cts.eduLink.application.service;

import com.cts.eduLink.application.classexception.CourseException;
import com.cts.eduLink.application.projection.CourseAttendanceProjection;
import com.cts.eduLink.application.projection.CourseSummaryProjection;
import com.cts.eduLink.application.repository.AttendanceRepository;
import com.cts.eduLink.application.repository.CourseRepository;
import com.cts.eduLink.application.util.AttendanceCalculator;
import com.cts.eduLink.application.util.DateUtils;
import com.cts.eduLink.application.util.ProjectionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j

public class AttendanceServiceImpl implements IAttendanceService{

    private final CourseRepository courseRepository;
    private final AttendanceRepository attendanceRepository;


    @Override
    public List<CourseAttendanceProjection> findAttendanceByCourse(Long studentId) {
        log.info("Fetching attendance report for Student ID: {}", studentId);
        List<CourseSummaryProjection> courseSummaryProjections = courseRepository.findCourseSummaryListByStudentId(studentId);
        if(courseSummaryProjections.isEmpty()){
            log.warn("Attendance request failed: Student {} has no course registrations.", studentId);
            throw new CourseException(studentId+" is not registered into any course", HttpStatus.NOT_FOUND);
        }
        log.debug("Found {} courses for student {}", courseSummaryProjections.size(), studentId);
        List<CourseAttendanceProjection> courseAttendanceProjections = new ArrayList<>();
        for(CourseSummaryProjection course: courseSummaryProjections){
            Long totalAttendedDays = attendanceRepository.countAttendanceByIdAndStudentId(course.getId(),studentId);
            if(totalAttendedDays >0L){
                LocalDateTime firstAttendanceDate = attendanceRepository.findFirstEnrollmentDate(course.getCourseId(),studentId);
                LocalDateTime lastAttendanceDate = attendanceRepository.findLastEnrollmentDate(course.getCourseId(),studentId);
                Long daysBetween = DateUtils.getCalendarDaysBetween(firstAttendanceDate,lastAttendanceDate);
                double attendancePercentage = AttendanceCalculator.calculateAttendance(totalAttendedDays,daysBetween);
                log.info("Course: {} | Attended: {} days | first Attended date: {} | last attended date: {} | Window: {} days | Rate: {}%",course.getCourseId(), totalAttendedDays, firstAttendanceDate,lastAttendanceDate, daysBetween, String.format("%.2f", attendancePercentage));
                CourseAttendanceProjection courseAttendanceProjection = ProjectionMapper.courseAttendanceProjection(course,attendancePercentage);
                courseAttendanceProjections.add(courseAttendanceProjection);
            }else{
                log.debug("Skipping Course {}: Zero attendance records found.", course.getCourseId());
            }
        }
        log.info("Completed attendance report for Student {}. Courses processed: {}", studentId, courseAttendanceProjections.size());
        return courseAttendanceProjections;
    }
}
