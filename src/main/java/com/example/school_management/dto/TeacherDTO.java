package com.example.school_management.dto;

import com.example.school_management.entity.Member;
import com.example.school_management.entity.Teacher;

public class TeacherDTO {
    Teacher teacher;
    Member member;


    public TeacherDTO(Teacher teacher, Member member) {
        this.teacher = teacher;
        this.member = member;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
