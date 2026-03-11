package com.project.quizzapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    private String username; // primary key

    @Column(name = "teacher_name", nullable = false)
    private String teacherName; // to map variable to column name in db

    private String email; // name already exists in db so no need for mapping

    @Column(name = "password_hash", nullable = false)
    private String passwordHash; // mapping to relevant column in db

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // mapping to relevant column in db

    // getters and setters for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // getters and setters for teacherName
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    // getters and setters for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // getters and setters for passwordHash
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // getters and setters for createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}