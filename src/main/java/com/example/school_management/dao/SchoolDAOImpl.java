package com.example.school_management.dao;

import com.example.school_management.entity.Course;
import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SchoolDAOImpl implements SchoolDAO{

    EntityManager em;

    @Autowired
    public SchoolDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void saveStudent(Student student) {
        System.out.println(student);
        em.persist(student);
    }

    @Override
    public void saveCourse(Course course) {
em.persist(course);

    }

    @Override
    public void assignCourseToTeacher() {

    }

    @Override
    public void acceptStudentInCourse() {

    }

    @Override
    public void gradeACourse() {

    }

    @Override
    public void saveTeacher(Teacher teacher) {
em.persist(teacher);
    }

    @Override
    public void saveMember(Member member) {
        em.persist(member);
    }

    @Override
    public Teacher findTeacherById(Integer id) {
        return null;
    }

    @Override
    public Course findCourseById(Integer id) {
        return null;
    }

    @Override
    public Student findStudentById(Integer id) {
        return null;
    }

    @Override
    public List<Course> findAvailableCourses() {
        return List.of();
    }
}
