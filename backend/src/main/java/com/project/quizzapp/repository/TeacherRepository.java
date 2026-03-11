package com.project.quizzapp.repository;

import com.project.quizzapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    // JpaRepository provides basic CRUD operations for Teacher entity
    boolean existsByUsername(String username); // to check if a teacher with the given username already exists
    boolean existsByEmail(String email); // to check if a teacher with the given email already exists
}
