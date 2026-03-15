-- Insert test data for CourseDashboard testing

INSERT INTO course (id, course_id, course_title, course_subject, course_grade_level, course_credit, course_status, course_rating) VALUES
(1, 101, 'Web Development Bootcamp', 'Computer Science', 'Beginner', 3, 'Active', 4.5);

INSERT INTO faculty (id, faculty_id, faculty_gender, student_address, faculty_year_of_experience, faculty_rating) VALUES
(1, 201, 'Male', 'Faculty Address 1', 10, 4.8);

INSERT INTO student (id, student_id, studentdob, student_gender, student_address, student_enrollment_date_time) VALUES
(1, 301, '2000-01-01', 'Male', 'Address 1', '2023-01-01T00:00:00');

INSERT INTO learning_material (id, learning_material_title, learning_material_uploaded_date, learning_material_status, course_id) VALUES
(1, 'Module 1 - Introduction.pdf', '2023-01-01T00:00:00', 'Active', 1),
(2, 'Module 2 - Basics.pdf', '2023-01-01T00:00:00', 'Active', 1);

INSERT INTO assignment_status (id, status, student_id, course_id) VALUES
(1, 'Pending', 1, 1),
(2, 'Pending', 1, 1),
(3, 'Pending', 1, 1),
(4, 'Pending', 1, 1),
(5, 'Pending', 1, 1);

INSERT INTO exam (id, exam_name, exam_local_date_time, exam_status, course_id) VALUES
(1, 'Web Development Exam', '2023-01-01T00:00:00', 'Active', 1);