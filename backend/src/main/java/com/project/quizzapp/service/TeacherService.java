package com.project.quizzapp.service;

import com.project.quizzapp.dto.*;
import com.project.quizzapp.entity.Teacher;
import com.project.quizzapp.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret; // Injects the JWT secret key from application.properties config file

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

    public Teacher loginTeacher(LoginRequest loginRequest) {
        Teacher teacher = teacherRepository.findByUsername(loginRequest.username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        // Performs JPA query to find teacher by username, if not found throws exception

        if (!passwordEncoder.matches(loginRequest.password, teacher.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        // Checks if entered password matches the stored password hash, if not throws
        // exception

        return teacher; // Returns teacher object on successful login
    }

    public String generateToken(Teacher teacher) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        // Creates a SecretKey object using the injected JWT secret key

        return Jwts.builder().setSubject(teacher.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(key)
                .compact();
        // Builds and returns a JWT token with the teacher's username as the subject,
        // issued at the current time, and an expiration time of 10 hours
    }
}