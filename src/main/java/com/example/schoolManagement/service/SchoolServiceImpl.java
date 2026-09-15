package com.example.schoolManagement.service;

import com.example.schoolManagement.dao.SchoolDAO;
import com.example.schoolManagement.dto.CourseDTO;
import com.example.schoolManagement.dto.GradeRow;
import com.example.schoolManagement.dto.GradesForm;
import com.example.schoolManagement.entity.*;
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
        schoolDAO.save(member);
    }


    @Override
    @Transactional
    public void saveStudent(Student student) {
        schoolDAO.save(student);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        schoolDAO.save(teacher);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return schoolDAO.findMemberByEmail(email);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfStudent(int studentId) {
        return schoolDAO.findEnrollmentsForStudent(studentId);
    }

    @Override
    public List<Course> findAvailableCourses(int studentId) {
        return schoolDAO.findAvailableCoursesForStudent(studentId);
    }

    @Override
    @Transactional
    public void deleteEnrollment(int id) {
 schoolDAO.deleteEnrollment(id);
    }

    @Override
    @Transactional
    public void saveEnrollment(String courseCode, int studentId) {
        Student s =schoolDAO.findStudentById(studentId);
        Course c =schoolDAO.findCourseByCode(courseCode);
        Enrollment e = new Enrollment(c,s);
        schoolDAO.save(e);
    }

    @Override
    public List<Enrollment> findEnrollmentRequestsForTeacher(int teacherId) {
        return schoolDAO.findEnrollmentRequestsForTeacher(teacherId);
    }

    @Override
    @Transactional
    public void acceptEnrollmentRequest(int requestId) {
        Enrollment e = schoolDAO.findEnrollmentById(requestId);
        e.setApproved(1);
        schoolDAO.save(e);
    }

    @Override
    public List<Course> findCoursesByTeacherId(int teacherId) {
        return schoolDAO.findCoursesForTeacher(teacherId);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code) {
        return schoolDAO.findEnrollmentsForCourse(code);
    }

    @Override
    @Transactional
    public void updateGrades(GradesForm gradesForm) {
        for(GradeRow row : gradesForm.getRows()) {
            Enrollment e = schoolDAO.findEnrollmentById(row.getEnrollmentId());
            e.setGrade(row.getGrade());

schoolDAO.save(e);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return schoolDAO.findAllTeachers();
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

        schoolDAO.save(course);
    }
}
