package com.cts.eduLink.application.repository;

import com.cts.eduLink.application.entity.Exam;
import com.cts.eduLink.application.entity.AppUser;
import com.cts.eduLink.application.entity.Faculty;
import com.cts.eduLink.application.projection.FacultyDetailProjection;
import com.cts.eduLink.application.projection.FacultyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    @Query("select f from Faculty f where f.facultyId = :facultyId")
    Optional<Faculty> findFacultyById(@Param("facultyId") Long facultyId);

    @Query("SELECT new com.cts.eduLink.application.projection.FacultyProjection(" +
            "u.userName, " +
            "u.userEmail, " +
            "u.phoneNumber, " +
            "f.facultyGender, " +
            "f.facultyYearOfExperience, " +
            "f.facultyAddress, " +
            "f.facultyRating) " +
            "FROM Faculty f JOIN f.appUser u " +
            "WHERE f.facultyId = :facultyId")
    Optional<FacultyProjection> findFacultyProfile(@Param("facultyId") Long facultyId);


    @Query("SELECT e FROM Exam e " +
            "JOIN e.course c " +
            "JOIN c.facultySet f " +
            "WHERE f.facultyId = :facultyId " +
            "AND e.examLocalDateTime < CURRENT_TIMESTAMP " +
            "ORDER BY e.examLocalDateTime ASC")
    List<Exam> findUpcomingExamsByFacultyId(
            @Param("facultyId") Long facultyId
    );

    @Query("SELECT COUNT(e) FROM Exam e " +
            "JOIN e.course c " +
            "JOIN c.facultySet f " +
            "WHERE f.facultyId = :facultyId " +
            "AND e.examLocalDateTime < CURRENT_TIMESTAMP")
    int getUpcomingExamsCount(@Param("facultyId") Long facultyId);



    @Query("select new com.cts.eduLink.application.projection.FacultyDetailProjection(a.userName, f.facultyRating,f.facultyYearOfExperience)"+
            " from Faculty f inner join f.appUser a where f.facultyRating>= :facultyRating and f.facultyRating<:facultyRating+1")
    List<FacultyDetailProjection> filterFacultyByRating(@Param("facultyRating") int facultyRating);

    @Query("select f.appUser from Faculty f where f.facultyId = :facultyId")
    Optional<AppUser> findAppUserByFacultyId(@Param("facultyId") Long facultyId);
}
