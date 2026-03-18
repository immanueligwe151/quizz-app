package com.project.quizzapp.repository;

import com.project.quizzapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    // JpaRepository provides basic CRUD operations for Teacher entity
    boolean existsByUsername(String username); // to check if a teacher with the given username already exists

    boolean existsByEmail(String email); // to check if a teacher with the given email already exists

    Optional<Teacher> findByUsername(String username); // returns teacher from db with the given username
}
