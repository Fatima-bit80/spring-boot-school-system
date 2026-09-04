package com.example.school_management.entity;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id")
    private int teacherId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Member member;

    @OneToMany(mappedBy = "teacher",
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Course> courses;

    public Teacher(String firstName, String lastName, String email, Member member, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.member = member;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", member=" + member +
                ", courses=" + courses +
                '}';
    }
}

