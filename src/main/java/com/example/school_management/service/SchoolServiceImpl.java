package com.example.school_management.service;

import com.example.school_management.dao.SchoolDAO;
import com.example.school_management.dto.CourseDTO;
import com.example.school_management.dto.GradesForm;
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

    @Override
    public List<Enrollment> findRequestsForTeacher(int teacherId) {
        return schoolDAO.findRequestsForTeacher(teacherId);
    }

    @Override
    public void acceptRequest(int requestId) {
        schoolDAO.acceptRequest(requestId);
    }

    @Override
    public List<Course> findCoursesByTeacherId(int teacherId) {
        return schoolDAO.findCoursesByTeacherId(teacherId);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code) {
        return schoolDAO.findEnrollmentsOfCourse(code);
    }

    @Override
    @Transactional
    public void updateGrades(GradesForm gradesForm) {
        schoolDAO.updateGrades(gradesForm);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return schoolDAO.getAllTeachers();
    }

    @Override
    @Transactional
    public void saveCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCode(courseDTO.getCode());
        course.setName(courseDTO.getName());
        course.setYear(courseDTO.getYear());

        Teacher teacher = schoolDAO.findTeacherById(courseDTO.getTeacherId());
        course.setTeacher(teacher);

        schoolDAO.saveCourse(course);
    }
}
