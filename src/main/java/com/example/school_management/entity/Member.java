package com.example.school_management.entity;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private int memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name="active")
    private int active;

    public Member(String password, String role) {
        this.password = password;
        this.role = role;
    }

    public Member(String password, String role, int active) {
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
