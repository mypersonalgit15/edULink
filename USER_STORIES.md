# EduLink Sprint 1 - User Stories (14 Days)

## Overview
**Sprint Duration:** 14 Days  
**Total Story Points:** 41  
**Target:** Achievable for 1-2 developers

---

## MODULE 1: COURSE MANAGEMENT

### US-001: Create New Course
- **User Story:** As an admin, I want to create a new course with title, subject, grade level, and credits, so that students can enroll in it.
- **Acceptance Criteria:**
  - Given: Admin is on course creation page
  - When: Admin fills in courseId, courseTitle, courseSubject, courseGradeLevel, courseCredit, courseStatus, courseRating
  - Then: Course is saved to database and success message is shown
  - AND: Course appears in course list
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for course creation
  - [ ] Validate input fields
  - [ ] Save to database
  - [ ] Return success response

---

### US-002: View Course Details
- **User Story:** As a student, I want to view course details (title, subject, credits, rating), so that I can decide to enroll.
- **Acceptance Criteria:**
  - Given: Student clicks on a course
  - When: Course ID is provided
  - Then: All course details are displayed
  - AND: Course rating and credit hours are visible
- **Story Points:** **2**
- **Priority:** High
- **Tasks:**
  - [ ] Create GET endpoint for course details
  - [ ] Return CourseDetailsProjection
  - [ ] Display all relevant fields

---

### US-003: Enroll Student in Course
- **User Story:** As a student, I want to enroll in a course, so that I can access course materials and assignments.
- **Acceptance Criteria:**
  - Given: Student is viewing a course
  - When: Student clicks "Enroll" button with valid studentId
  - Then: Student is added to the course roster
  - AND: Student can now access course content
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for enrollment
  - [ ] Link student to course
  - [ ] Return confirmation message

---

## MODULE 2: LEARNING MATERIAL MANAGEMENT

### US-004: Upload Learning Material
- **User Story:** As a faculty, I want to upload learning materials (documents, videos) to a course, so that students can access course content.
- **Acceptance Criteria:**
  - Given: Faculty is in course management section
  - When: Faculty uploads learning material with title, upload date, status
  - Then: Material is saved and linked to the course
  - AND: Material status is set to "Published"
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for learning material
  - [ ] Link material to course
  - [ ] Set default status to "Published"

---

### US-005: View Course Materials
- **User Story:** As a student, I want to view all learning materials for my enrolled course, so that I can study the content.
- **Acceptance Criteria:**
  - Given: Student is enrolled in a course
  - When: Student navigates to course content section
  - Then: All published learning materials are displayed
  - AND: Materials can be downloaded/accessed
- **Story Points:** **2**
- **Priority:** High
- **Tasks:**
  - [ ] Create GET endpoint for course materials
  - [ ] Filter by course ID
  - [ ] Return list of materials

---

## MODULE 3: ASSIGNMENT MANAGEMENT

### US-006: Create Assignment
- **User Story:** As a faculty, I want to create assignments for a course, so that students can complete coursework.
- **Acceptance Criteria:**
  - Given: Faculty is in assignment creation section
  - When: Faculty creates assignment with status "Pending" for a course
  - Then: Assignment is created and linked to the course
  - AND: Assignment appears in student's assignment list
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for assignment
  - [ ] Link assignment to course and student
  - [ ] Set initial status to "Pending"

---

### US-007: View Assignments
- **User Story:** As a student, I want to view all assignments for my course, so that I know what work needs to be done.
- **Acceptance Criteria:**
  - Given: Student is enrolled in a course
  - When: Student navigates to assignments section
  - Then: All assignments for that course are displayed
  - AND: Assignment status (Pending/Completed) is shown
- **Story Points:** **2**
- **Priority:** High
- **Tasks:**
  - [ ] Create GET endpoint for assignments
  - [ ] Filter by course and student ID
  - [ ] Return status for each assignment

---

### US-008: Complete Assignment
- **User Story:** As a student, I want to mark an assignment as complete, so that my progress is tracked.
- **Acceptance Criteria:**
  - Given: Student has a pending assignment
  - When: Student clicks "Complete" on the assignment
  - Then: Assignment status changes to "Completed"
  - AND: Faculty can see completed assignments
- **Story Points:** **2**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint to complete assignment
  - [ ] Update assignment status to "Completed"
  - [ ] Log completion timestamp

---

## MODULE 4: STUDENT MANAGEMENT

### US-012: Create Student Profile
- **User Story:** As an admin, I want to create student profiles with personal details, so that students can use the system.
- **Acceptance Criteria:**
  - Given: Admin is in student creation section
  - When: Admin enters studentId, DOB, gender, address, enrollment date
  - Then: Student profile is created
  - AND: Student receives login credentials
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for student creation
  - [ ] Validate all required fields
  - [ ] Save to database
  - [ ] Return success response

---

### US-013: View Student Profile
- **User Story:** As a student, I want to view my profile details, so that I can verify my information.
- **Acceptance Criteria:**
  - Given: Student is logged in
  - When: Student navigates to profile section
  - Then: All student details are displayed
  - AND: Profile can be updated
- **Story Points:** **2**
- **Priority:** Medium
- **Tasks:**
  - [ ] Create GET endpoint for student profile
  - [ ] Return all student details
  - [ ] Display enrolled courses

---

## MODULE 6: FACULTY MANAGEMENT

### US-014: Create Faculty Profile
- **User Story:** As an admin, I want to create faculty profiles with experience and rating, so that they can manage courses.
- **Acceptance Criteria:**
  - Given: Admin is in faculty creation section
  - When: Admin enters facultyId, gender, address, experience, rating
  - Then: Faculty profile is created
  - AND: Faculty can create courses and assignments
- **Story Points:** **3**
- **Priority:** High
- **Tasks:**
  - [ ] Create POST endpoint for faculty creation
  - [ ] Validate input fields
  - [ ] Save to database
  - [ ] Return success response

---

### US-015: View Faculty Details
- **User Story:** As a student, I want to view faculty information and rating, so that I know who is teaching the course.
- **Acceptance Criteria:**
  - Given: Student is viewing a course
  - When: Student clicks on faculty name
  - Then: Faculty profile with experience and rating is displayed
  - AND: Faculty contact information is available
- **Story Points:** **2**
- **Priority:** Medium
- **Tasks:**
  - [ ] Create GET endpoint for faculty details
  - [ ] Return all faculty information
  - [ ] Display rating and experience

---

## SPRINT SUMMARY TABLE

| Module | Story ID | Story Points | Priority |
|--------|----------|-------------|----------|
| Course | US-001 | 3 | High |
| Course | US-002 | 2 | High |
| Course | US-003 | 3 | High |
| Learning Material | US-004 | 3 | High |
| Learning Material | US-005 | 2 | High |
| Assignment | US-006 | 3 | High |
| Assignment | US-007 | 2 | High |
| Assignment | US-008 | 2 | High |
| Exam | US-009 | 3 | High |
| Exam | US-010 | 5 | Critical |
| Exam | US-011 | 3 | High |
| Student | US-012 | 3 | High |
| Student | US-013 | 2 | Medium |
| Faculty | US-014 | 3 | High |
| Faculty | US-015 | 2 | Medium |

---

## SPRINT TOTALS

| Metric | Value |
|--------|-------|
| Total User Stories | 15 |
| Total Story Points | 41 |
| Critical Priority | 1 |
| High Priority | 12 |
| Medium Priority | 2 |
| Sprint Duration | 14 Days |
| Estimated Capacity | 1-2 Developers |

---

## NOTES

- All endpoints are RESTful APIs
- All POST requests accept JSON payloads
- All GET requests return JSON responses
- Database: H2 (In-Memory)
- Framework: Spring Boot 4.0.3
- Java Version: 21

---

**Last Updated:** March 11, 2026  
**Created By:** Development Team
