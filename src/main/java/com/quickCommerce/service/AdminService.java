package com.quickCommerce.service;


import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.entity.Admin;
import com.quickCommerce.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private EmailService emailService;

    public String sendOtpToEmail(String username, String email) {

        // ✅ Generate 6-digit OTP
        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));

        // ✅ Create or update Admin object
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setOtp(otp);
        admin.setOtpExpiry(Instant.now().plusSeconds(300)); // OTP valid for 5 minutes

        // ✅ Save OTP info in DB
        repo.save(admin);

        // ✅ Send OTP via email
        emailService.sendOtp(email, otp);

        return "OTP has been sent to " + email;
    }

    public AdminDTO verifyOtpAndAddPassword(String email, String otp,String password) {
        Admin admin = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email Not Found."));

        if (!otp.equals(admin.getOtp()) || Instant.now().isAfter(admin.getOtpExpiry())) {
            throw new RuntimeException("OTP expired or invalid.");
        }

        admin.setPassword(password); // You may replace this with the actual password logic
        admin.setRole("ADMIN");
        admin.setOtp(otp);
        admin.setOtpExpiry(Instant.now().plusSeconds(300));

        Admin savedAdmin = repo.save(admin);

        return new AdminDTO(savedAdmin); // Only id, username, email, and role will be returned
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = repo.findAll();

        return admins.stream()
                .map(AdminDTO::new)
                .collect(Collectors.toList());
    }
    public AdminDTO updateAdmins(Admin admin) {
        Admin existingAdmin = repo.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + admin.getId()));

        // Update only necessary fields (optional, recommended for safety)
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setRole(admin.getRole());
        // You can add more fields as needed

        Admin updatedAdmin = repo.save(existingAdmin);
        return new AdminDTO(updatedAdmin);
    }
    public void deleteAdmin(Long id) {
        boolean exists = repo.existsById(id);
        if (!exists) {
            throw new RuntimeException("Admin not found with ID: " + id);
        }
        repo.deleteById(id);
    }
    public AdminDTO getAdminById(Long id) {
        Admin admin = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
        return new AdminDTO(admin);
    }
}

