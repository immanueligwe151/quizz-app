package com.project.quizzapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizzapp.dto.*;
import com.project.quizzapp.entity.Teacher;
import com.project.quizzapp.service.TeacherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    private final TeacherService teacherService;

    public AuthController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        try {
            Teacher teacher = teacherService.registerTeacher(req);
            // Registers new teacher using the provided SignupRequest object and returns the
            // created Teacher entity

            return ResponseEntity.ok()
                    .header("Set-Cookie", createSessionCookie(teacherService.generateToken(teacher)).toString())
                    .body(createLoginResponse(teacher));
            // Returns a ResponseEntity with the LoginResponse object in the body and sets a
            // cookie with the JWT token generated for the logged-in teacher, also
            // automatically logs in the user after signing up
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Teacher teacher = teacherService.loginTeacher(req);
            // LoginResponse object to pass just the username and teacher to frontend

            return ResponseEntity.ok()
                    .header("Set-Cookie", createSessionCookie(teacherService.generateToken(teacher)).toString())
                    .body(createLoginResponse(teacher));
            // Returns a ResponseEntity with the LoginResponse object in the body and sets a
            // cookie with the JWT token generated for the logged-in teacher
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    private LoginResponse createLoginResponse(Teacher teacher) {
        return new LoginResponse(teacher.getUsername(), teacher.getTeacherName());
        // Creates and returns a LoginResponse object using the username and teacher
        // name from the provided Teacher entity
    }

    private ResponseCookie createSessionCookie(String token) {
        long maxAge = 60L * 60 * 10; // 10 hours in seconds
        return ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false) // Set to true in production when using HTTPS
                .path("/")
                .maxAge(maxAge)
                .sameSite("Lax")
                .build();
        // Creates and returns a ResponseCookie object with the name "token", the
        // provided JWT token as the value, and sets it to be HTTP-only, secure (in
        // production), with a path of "/" and a max age of 10 hours
    }

}
