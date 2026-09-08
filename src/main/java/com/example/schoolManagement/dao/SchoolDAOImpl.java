package com.example.schoolManagement.dao;

import com.example.schoolManagement.dto.GradeRow;
import com.example.schoolManagement.dto.GradesForm;
import com.example.schoolManagement.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    public void saveTeacher(Teacher teacher) {
em.persist(teacher);
    }

    @Override
    public void saveMember(Member member) {
        em.persist(member);
    }



    @Override
    public List<Course> findAvailableCourses(int studentId) {

        TypedQuery<Course> c = em.createQuery(
                "SELECT c1 " +
                " FROM Course c1" +
                " WHERE c1.code NOT IN(" +
                " SELECT c.code FROM Enrollment e "+
                " JOIN e.course c" +
                "  WHERE e.student.id = :studentId)", Course.class);

        c.setParameter("studentId", studentId);



        return c.getResultList();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return em.find(Member.class, email);
    }

    @Override
    public Student findStudentById(int studentId) {
        return em.find(Student.class, studentId);
    }

    @Override
    public Course findCourseByCode(String courseCode) {
        return em.find(Course.class, courseCode);
    }

    @Override
    public List<Enrollment> findEnrollmentsOfStudent(int studentId) {
        TypedQuery<Enrollment> q = em.createQuery(
                "SELECT e FROM Enrollment e " +
                        "JOIN FETCH e.course c " +
                        "JOIN FETCH c.teacher " +
                        "WHERE e.student.id = :studentId",
                Enrollment.class);

        q.setParameter("studentId", studentId);



        return q.getResultList();


    }

    @Override
    public void saveEnrollment(String courseCode, int studentId) {
        Student s = findStudentById(studentId);
        Course c = findCourseByCode(courseCode);
        Enrollment e = new Enrollment(c,s);
        em.persist(e);
    }

    @Override
    public void deleteEnrollment(int id) {
        Enrollment enrollment = em.find(Enrollment.class, id);
        em.remove(enrollment);
    }

    @Override
    public List<Enrollment> findRequestsForTeacher(int teacherId) {

        TypedQuery<Enrollment> q = em.createQuery(
                "SELECT e FROM Enrollment e " +
                        "JOIN FETCH e.course c " +
                        "JOIN c.teacher " +
                        "JOIN FETCH e.student s " +
                        "WHERE c.teacher.id = :teacherId AND " +
                        "e.approved = 0",
                Enrollment.class);

        q.setParameter("teacherId", teacherId);
        return q.getResultList();

    }

    @Override
    public void acceptRequest(int requestId) {
        Enrollment e = em.find(Enrollment.class, requestId);
        e.setApproved(1);
        em.persist(e);
    }

    @Override
    public List<Course> findCoursesByTeacherId(int teacherId) {
        TypedQuery<Course> q = em.createQuery(
                "SELECT t.courses " +
                        "FROM Teacher t " +
                        "WHERE t.teacherId = :teacherId",
                Course.class);

        q.setParameter("teacherId", teacherId);
        return q.getResultList();
    }

    @Override
    public List<Enrollment> findEnrollmentsOfCourse(String code) {

        TypedQuery<Enrollment> q = em.createQuery(
                "SELECT e FROM Enrollment e " +
                        "JOIN FETCH e.student s " +
                        "WHERE e.course.code = :code AND e.approved = 1"
                ,
                Enrollment.class);

        q.setParameter("code", code);
        return q.getResultList();
    }

    @Override
    public void updateGrades(GradesForm gradesForm) {
        for(GradeRow row : gradesForm.getRows()) {
            Enrollment e = em.find(Enrollment.class, row.getEnrollmentId());
            e.setGrade(row.getGrade());
            em.persist(e);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        TypedQuery<Teacher> q = em.createQuery("FROM Teacher", Teacher.class);
       return q.getResultList();

    }

    @Override
    public Teacher findTeacherById(int teacherId) {
        Teacher t = em.find(Teacher.class, teacherId);
        return t;

    }
}
