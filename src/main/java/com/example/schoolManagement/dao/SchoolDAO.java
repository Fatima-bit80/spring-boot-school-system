package com.example.schoolManagement.dao;

import com.example.schoolManagement.dto.GradesForm;
import com.example.schoolManagement.entity.*;

import java.util.List;

public interface SchoolDAO {
    //guests can:
    void saveStudent(Student student);
    void saveTeacher(Teacher teacher);
    void saveMember(Member member);



    // admin can:
    void saveCourse(Course course);



    List<Course> findAvailableCourses(int studentId);



    Member findMemberByEmail(String email);
    public Student findStudentById(int studentId);
    public Course findCourseByCode(String courseCode);


    List<Enrollment> findEnrollmentsOfStudent(int studentId);

    void saveEnrollment(String courseCode, int studentId);
    void deleteEnrollment(int id);

    List<Enrollment> findRequestsForTeacher(int teacherId);

    void acceptRequest(int requestId);

    List<Course> findCoursesByTeacherId(int teacherId);

    List<Enrollment> findEnrollmentsOfCourse(String code);

    void updateGrades(GradesForm gradesForm);

    List<Teacher> getAllTeachers();

    Teacher findTeacherById(int teacherId);
}
