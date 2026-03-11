package com.project.quizzapp.service;

import com.project.quizzapp.dto.SignupRequest;
import com.project.quizzapp.entity.Teacher;
import com.project.quizzapp.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Teacher registerTeacher(SignupRequest signupRequest) {
        // to check if username or email already exists
        if (teacherRepository.existsByUsername(signupRequest.username)) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (teacherRepository.existsByEmail(signupRequest.email)) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // to check if the inputted passwords match
        if (!signupRequest.password.equals(signupRequest.confirmPassword)) {
            String message = "Passwords do not match. Password 1: " + signupRequest.password + ", Password 2: "
                    + signupRequest.confirmPassword;
            throw new IllegalArgumentException(message);
        }

        // Create a new Teacher entity
        Teacher teacher = new Teacher();
        teacher.setUsername(signupRequest.username);
        teacher.setTeacherName(signupRequest.name);
        teacher.setEmail(signupRequest.email);
        teacher.setPasswordHash(passwordEncoder.encode(signupRequest.password)); // Hash the password
        teacher.setCreatedAt(LocalDateTime.now());

        // Save the teacher to the database
        return teacherRepository.save(teacher);
    }
}