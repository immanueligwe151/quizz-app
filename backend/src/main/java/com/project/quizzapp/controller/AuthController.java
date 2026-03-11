package com.project.quizzapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizzapp.dto.SignupRequest;
import com.project.quizzapp.entity.Teacher;
import com.project.quizzapp.service.TeacherService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final TeacherService teacherService;

    public AuthController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        Teacher teacher = teacherService.registerTeacher(req);
        return ResponseEntity.ok(teacher);
    }
}
