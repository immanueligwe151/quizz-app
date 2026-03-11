package com.project.quizzapp.dto;

public class SignupRequest {
    public String name;
    public String username;
    public String email;
    public String password;
    public String confirmPassword;

    public SignupRequest(String name, String username, String email, String password, String confirmPassword) {
        // Default constructor
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
