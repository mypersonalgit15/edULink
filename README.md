classDiagram
    direction TB

    class AppUser {
        +Long id
        +Long phoneNumber
        +String userEmail
        +Long userId
        +String userName
    }

    class Attendance {
        +Long id
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
    }

    class Exam {
        +Long id
        +LocalDateTime examLocalDateTime
        +String examName
        +String examStatus
    }

    class Faculty {
        +Long id
        +String facultyGender
        +Long facultyId
        +double facultyRating
        +int facultyYearOfExperience
        +String studentAddress
    }

    class Grade {
        +Long id
        +String grade
        +double score
        +String status
    }

    class LearningMaterial {
        +Long id
        +File learningMaterialFile
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
        +Long id
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

    AbstractAuditable --|> AbstractPersistable
    AppUser "0..1" <--> "0..*" Notification : notifies
    AppUser "0..1" <--> "0..1" Role : has role
    Course "0..1" <--> "0..*" Exam : includes
    Course "0..*" <--> "0..*" Faculty : taught by
    Course "0..1" <--> "0..*" LearningMaterial : contains
    Course "0..1" <--> "0..*" PerformanceMetric : measures
    Exam "0..1" <--> "0..*" Grade : results in
    Student "0..1" <--> "0..*" Attendance : records
    Student "0..*" <--> "0..*" Course : enrolled in
    Student "0..1" <--> "0..*" Grade : receives
    Student "0..1" <--> "0..*" PerformanceMetric : tracked by
