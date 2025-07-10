package com.quickCommerce.service;

<<<<<<< HEAD
public interface EmailService {
    void sendOtpToEmail(String toEmail, String otp);
}
=======

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP");
        message.setText("Your is OTP: " + otp);

        mailSender.send(message);
    }
}


>>>>>>> c3ddd5a19a7153dee0332aceddfd178c4af7161b
