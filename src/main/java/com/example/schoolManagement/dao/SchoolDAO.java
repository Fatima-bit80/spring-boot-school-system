package com.example.schoolManagement.dao;

import com.example.schoolManagement.entity.*;

import java.util.List;

public interface SchoolDAO {
    //accounts
    Member findMemberByEmail(String email);
    Student findStudentById(int studentId);
    Teacher findTeacherById(int teacherId);
    List<Teacher> findAllTeachers();
    void save(Member member);
    void save(Student student);
    void save(Teacher teacher);


    //courses:
    Course findCourseByCode(String courseCode);
    List<Course> findAvailableCoursesForStudent(int studentId);
    List<Course> findCoursesForTeacher(int teacherId);
    void save(Course course);


    //enrollments:
    Enrollment findEnrollmentById(int id);
    List<Enrollment> findEnrollmentsForStudent(int studentId);
    List<Enrollment> findEnrollmentsForCourse(String code);
    List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId);
    void save(Enrollment e);
    void deleteEnrollment(int id);
}
