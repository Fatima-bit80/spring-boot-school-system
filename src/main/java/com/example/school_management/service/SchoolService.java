package com.example.school_management.service;

import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.Teacher;

public interface SchoolService {

    void saveMember(Member member);
  void saveStudent(Student student);
  void saveTeacher(Teacher teacher);

}
