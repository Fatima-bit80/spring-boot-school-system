package com.example.school_management.dao;

import com.example.school_management.entity.Course;
import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;

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
    Teacher findTeacherById(Integer id);
    Course findCourseById(Integer id);
    Student findStudentById(Integer id);

    List<Course> findAvailableCourses();





}
