package com.quickCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OtpVerificationDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "OTP is required")
    @Size(min = 6, max = 6, message = "OTP must be 6 digits")
    private String otp;

    @NotBlank(message = "Password is required")
    @Size(min = 10, message = "Password must be at least 6 characters")
    private String password;

    // ✅ Default constructor
    public OtpVerificationDTO() {
    }

    // ✅ All-args constructor
    public OtpVerificationDTO(String email, String otp, String password) {
        this.email = email;
        this.otp = otp;
        this.password = password;
    }

    // ✅ Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
