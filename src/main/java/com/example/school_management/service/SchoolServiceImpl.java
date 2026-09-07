package com.example.school_management.service;

import com.example.school_management.dao.SchoolDAO;
import com.example.school_management.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements  SchoolService {

    private SchoolDAO schoolDAO;

    @Autowired
    public SchoolServiceImpl(SchoolDAO schoolDAO) {
        this.schoolDAO = schoolDAO;
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        schoolDAO.saveMember(member);
    }


    @Override
    @Transactional
    public void saveStudent(Student student) {
        schoolDAO.saveStudent(student);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        schoolDAO.saveTeacher(teacher);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return schoolDAO.findMemberByEmail(email);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfStudent(int studentId) {
        return schoolDAO.findEnrollmentsOfStudent(studentId);
    }

    @Override
    public List<Course> findAvailableCourses(int studentId) {
        return schoolDAO.findAvailableCourses(studentId);
    }

    @Override
    @Transactional
    public void deleteEnrollment(int id) {
 schoolDAO.deleteEnrollment(id);
    }

    @Override
    @Transactional
    public void saveEnrollment(String courseCode, int studentId) {
        schoolDAO.saveEnrollment(courseCode, studentId);
    }
}
