package com.quickCommerce.service;

import com.quickCommerce.entity.User;
import com.quickCommerce.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    // For demonstration only — in real apps, store OTP securely per user!
    private String currentOtp;

    @Override
    public String register(User user) {
        user.setVerified(false);
        userRepository.save(user);

        String otp = generateOtp();
        currentOtp = otp; // For demo only — not safe for multiple users

        sendOtpEmail(user.getEmail(), otp);

        return "Registered successfully. OTP sent to email.";
    }

    @Override
    public String verifyOtp(String email, String otp) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            if (!otp.equals(currentOtp)) {
                return "Invalid OTP.";
            }
            User user = optionalUser.get();
            user.setVerified(true);
            userRepository.save(user);
            return "OTP verified successfully.";
        } else {
            return "User not found.";
        }
    }

    @Override
    public String login(User loginUser) {
        Optional<User> optionalUser = userRepository.findByEmail(loginUser.getEmail());
        if (optionalUser.isEmpty()) {
            return "User not found.";
        }

        User user = optionalUser.get();

        if (!user.isVerified()) {
            return "User not verified.";
        }

        if (!user.getPassword().equals(loginUser.getPassword())) {
            return "Invalid credentials.";
        }

        return "Login successful.";
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    private void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);

        javaMailSender.send(message);
    }
}
