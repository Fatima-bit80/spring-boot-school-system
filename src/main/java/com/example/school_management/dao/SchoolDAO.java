package com.example.school_management.dao;

import com.example.school_management.entity.*;

import java.util.List;

public interface SchoolDAO {
    //guests can:
    void saveStudent(Student student);
    void saveTeacher(Teacher teacher);
    void saveMember(Member member);



    // admin can:
    void saveCourse(Course course);
    void assignCourseToTeacher();

    //teacher can:
    void acceptStudentInCourse();
    void gradeACourse();

    //find


    List<Course> findAvailableCourses(int studentId);



    Member findMemberByEmail(String email);
    public Student findStudentById(int studentId);
    public Course findCourseByCode(String courseCode);


    List<Enrollment> findEnrollmentsOfStudent(int studentId);

    void saveEnrollment(String courseCode, int studentId);
    void deleteEnrollment(int id);
}
