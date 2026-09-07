package com.example.school_management.service;

import com.example.school_management.entity.*;

import java.util.List;

public interface SchoolService {

    void saveMember(Member member);
  void saveStudent(Student student);
  void saveTeacher(Teacher teacher);

    Member findMemberByEmail(String email);


    List<Enrollment> findEnrollmentsOfStudent(int studentId);
  List<Course> findAvailableCourses(int studentId);
    void deleteEnrollment(int id);

  void saveEnrollment(String courseCode, int studentId);

  List<Enrollment> findRequestsForTeacher(int teacherId);

  void acceptRequest(int requestId);
}
