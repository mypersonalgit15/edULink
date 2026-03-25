# 📘 Project Class Diagram

This repository contains the domain model for the project.  
The following diagram illustrates the relationships between entities.

```mermaid
classDiagram
    direction TB

    class AppUser {
        +Long id
        +Long phoneNumber
        +String userEmail
        +String userName
        +String userPassword
    }

    class Assignment {
        +Long id
        +LocalDateTime assignmentCreatedDate
        +String assignmentDescription
        +LocalDateTime assignmentDueDate
        +Long assignmentId
        +String assignmentStatus
        +String assignmentTitle
        +int totalMarks
    }

    class AssignmentStatus {
        +Long id
        +double score
        +String status
        +LocalDateTime submissionDate
        +String submissionUrl
    }

    class Attendance {
        +Long id
        +Long attendanceId
        +LocalDateTime localDateTime
    }

    class Course {
        +Long id
        +int courseCredit
        +String courseGradeLevel
        +Long courseId
        +double courseRating
        +String courseStatus
        +String courseSubject
        +String courseTitle
        +Long totalCourseRatingCount
    }

    class Exam {
        +Long id
        +int candidates
        +Long examId
        +LocalDateTime examLocalDateTime
        +String examName
        +String examStatus
    }

    class Faculty {
        +Long id
        +String facultyAddress
        +String facultyGender
        +Long facultyId
        +double facultyRating
        +int facultyYearOfExperience
        +long totalFacultyRatingCount
    }

    class FeedBack {
        +Long id
        +String message
        +double rating
    }

    class Grade {
        +Long id
        +String grade
        +Long gradeId
        +double score
        +String status
    }

    class LearningMaterial {
        +Long id
        +String learningMaterialFile
        +String learningMaterialStatus
        +String learningMaterialTitle
        +LocalDateTime learningMaterialUploadedDate
    }

    class Notification {
        +Long id
        +String notificationMessage
    }

    class PerformanceMetric {
        +Long id
        +LocalDate localDate
        +Long metricId
        +double score
        +String status
    }

    class Role {
        +Long roleId
        +String roleName
    }

    class Student {
        +Long id
        +String studentAddress
        +LocalDate studentDOB
        +LocalDateTime studentEnrollmentDateTime
        +String studentGender
        +Long studentId
    }

    %% Relationships
    AppUser "0..1" <--> "0..1" Faculty
    AppUser "0..1" <--> "0..*" FeedBack
    AppUser "0..1" <--> "0..*" Notification
    AppUser "0..*" <--> "0..1" Role
    AppUser "0..1" <--> "0..1" Student
    Assignment "0..1" <--> "0..*" AssignmentStatus
    Assignment "0..*" --> "0..1" Course
    AssignmentStatus "0..*" --> "0..1" Course
    AssignmentStatus "0..*" --> "0..1" Student
    Attendance "0..*" <--> "0..1" Course
    Attendance "0..*" <--> "0..1" Student
    Course "0..1" <--> "0..*" Exam
    Course "0..*" <--> "0..*" Faculty
    Course "0..1" <--> "0..*" LearningMaterial
    Course "0..1" <--> "0..*" PerformanceMetric
    Course "0..*" <--> "0..*" Student
    Exam "0..1" <--> "0..*" Grade
    Grade "0..*" <--> "0..1" Student
    PerformanceMetric "0..*" <--> "0..1" Student
