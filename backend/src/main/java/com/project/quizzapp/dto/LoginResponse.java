package com.project.quizzapp.dto;

public class LoginResponse {
    public String username;
    public String teacherName;

    public LoginResponse(String username, String teacherName) {
        this.username = username;
        this.teacherName = teacherName;
    }
}
