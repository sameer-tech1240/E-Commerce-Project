package com.quickCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminSendOtpRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    // ✅ Constructors
    public AdminSendOtpRequest() {
    }

    public AdminSendOtpRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // ✅ Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
