package com.example.schoolManagement.dao;

import com.example.schoolManagement.dto.GradesForm;
import com.example.schoolManagement.entity.*;

import java.util.List;

public interface SchoolDAO {
    //accounts
    void saveMember(Member member);
    void saveStudent(Student student);
    void saveTeacher(Teacher teacher);
    Member findMemberByEmail(String email);
    Student findStudentById(int studentId);
    Teacher findTeacherById(int teacherId);
    List<Teacher> getAllTeachers();

    //courses:
    void saveCourse(Course course);
    List<Course> findAvailableCourses(int studentId);
    Course findCourseByCode(String courseCode);
    List<Course> findCoursesByTeacherId(int teacherId);

    //enrollments:
    List<Enrollment> findEnrollmentsOfStudent(int studentId);
    List<Enrollment> findEnrollmentsOfCourse(String code);
    void saveEnrollment(String courseCode, int studentId);
    void deleteEnrollment(int id);
    List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId);
    void acceptEnrollmentRequest(int requestId);
    void updateGrades(GradesForm gradesForm);

}
