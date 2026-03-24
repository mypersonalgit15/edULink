package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("select count(a) from Attendance a where a.course.id = :id and a.student.studentId = :studentId")
    Long countAttendanceByIdAndStudentId(Long id, Long studentId);


    @Query("SELECT min(a.localDateTime) from Attendance a where a.course.courseId = :courseId and a.student.studentId = :studentId")
    LocalDateTime findFirstEnrollmentDate(Long courseId, Long studentId);

    @Query("SELECT max(a.localDateTime) from Attendance a where a.course.courseId = :courseId and a.student.studentId = :studentId")
    LocalDateTime findLastEnrollmentDate(Long courseId, Long studentId);


}