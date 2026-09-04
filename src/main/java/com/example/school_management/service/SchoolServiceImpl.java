package com.example.school_management.service;

import com.example.school_management.dao.SchoolDAO;
import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
