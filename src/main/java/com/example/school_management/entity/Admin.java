package com.example.school_management.entity;



import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "id")
    private int adminId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;


    public Admin(String firstName, String lastName, Member member) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.member = member;
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





    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", member=" + member +
                '}';
    }
}

