package com.quickCommerce.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.dto.OtpVerificationDTO;
import com.quickCommerce.entity.Admin;
import com.quickCommerce.repo.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private EmailService emailService;

    // ✅ 1. Send OTP to Email
    public String sendOtpToEmail(String username, String email) {
        Optional<Admin> existingAdmin = repo.findByEmail(email);
        if (existingAdmin.isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setOtp(otp);
        admin.setOtpExpiry(Instant.now().plusSeconds(300)); // 5 min validity

        repo.save(admin);
        emailService.sendOtp(email, otp);
        return "OTP has been sent to " + email;
    }

    // ✅ 2. Verify OTP & Set Password
    public String verifyOtpAndRegister(OtpVerificationDTO request) {
        Admin admin = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found."));

        if (!admin.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP.");
        }

        if (Instant.now().isAfter(admin.getOtpExpiry())) {
            throw new RuntimeException("OTP expired.");
        }

        admin.setPassword(request.getPassword()); // 🔐 You can hash this password
        admin.setOtp(null); // Clear OTP
        admin.setOtpExpiry(null);
        admin.setRole("ADMIN");

        repo.save(admin);
        return "OTP verified and password set successfully.";
    }

    // ✅ 3. Get All Admins
    public List<AdminDTO> getAllAdmins() {
        return repo.findAll().stream()
                .map(AdminDTO::new)
                .collect(Collectors.toList());
    }

    // ✅ 4. Update Admin
    public AdminDTO updateAdmins(Admin admin) {
        Admin existingAdmin = repo.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + admin.getId()));

        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setEmail(admin.getEmail());
        existingAdmin.setRole(admin.getRole());

        Admin updatedAdmin = repo.save(existingAdmin);
        return new AdminDTO(updatedAdmin);
    }

    // ✅ 5. Delete Admin
    public void deleteAdmin(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Admin not found with ID: " + id);
        }
        repo.deleteById(id);
    }

    // ✅ 6. Get Admin by ID
    public AdminDTO getAdminById(Long id) {
        Admin admin = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
        return new AdminDTO(admin);
    }
}
