package com.example.school_management.dto;

import com.example.school_management.entity.Member;
import com.example.school_management.entity.Student;

public class StudentDTO {

    private Student student;
    private Member  member;

    public StudentDTO(Student student, Member member) {
        this.student = student;
        this.member = member;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
