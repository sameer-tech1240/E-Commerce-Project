package com.quickCommerce.implement;

import com.quickCommerce.deliveryentity.DeliveryBoy;
import com.quickCommerce.dto.DeliveryBoyDTO;
import com.quickCommerce.repo.DeliveryBoyRepository;
import com.quickCommerce.service.EmailService;
import com.quickCommerce.service.RegisterService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final DeliveryBoyRepository repository;
    private final EmailService emailService;

    // Temporary memory to store user info until OTP is verified
    private final Map<String, DeliveryBoyDTO> otpMap = new HashMap<>();
    private final Map<String, String> otpStore = new HashMap<>();

    public RegisterServiceImpl(DeliveryBoyRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    @Override
    public String registerDeliveryBoy(DeliveryBoyDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            return "This email is already registered!";
        }

        // Generate OTP
        String otp = String.format("%04d", new Random().nextInt(10000));

        // Store data temporarily
        otpMap.put(dto.getEmail(), dto);
        otpStore.put(dto.getEmail(), otp);

        // Send OTP
        emailService.sendOtpToEmail(dto.getEmail(), otp);

        return "OTP sent to email!";
    }

    @Override
    public String verifyOtp(String email, String otp) {
        if (!otpStore.containsKey(email)) return "No OTP request found for this email!";
        if (!otpStore.get(email).equals(otp)) return "Invalid OTP!";

        DeliveryBoyDTO dto = otpMap.get(email);
        if (dto == null) return "User data not found in memory!";

        // Save to DB
        DeliveryBoy boy = new DeliveryBoy();
        boy.setName(dto.getName());
        boy.setEmail(dto.getEmail());
        boy.setPassword(dto.getPassword());
        boy.setAddress(dto.getAddress());
        boy.setOtp(otp);
        boy.setOtpGeneratedTime(LocalDateTime.now());
        boy.setRegisteredAt(LocalDateTime.now());
        boy.setActive(false);

        repository.save(boy);

        // Clear from memory
        otpMap.remove(email);
        otpStore.remove(email);

        return "OTP verified. User registered successfully.";
    }
}
