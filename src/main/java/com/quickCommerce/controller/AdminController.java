package com.quickCommerce.controller;


import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.dto.AdminSendOtpRequest;
import com.quickCommerce.dto.OtpVerificationDTO;
import com.quickCommerce.entity.Admin;
import com.quickCommerce.service.AdminService;
import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.entity.Admin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // OTP send API
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody AdminSendOtpRequest request) {
        String message = adminService.sendOtpToEmail(request.getUsername(), request.getEmail());
        return ResponseEntity.ok(message);
    }


    // OTP verify Password set API
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerificationDTO request) {
        String response = adminService.verifyOtpAndRegister(request);
        return ResponseEntity.ok(response);
    }

    //All admins find
    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @PutMapping("/update")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody Admin admin) {
        AdminDTO updatedAdmin = adminService.updateAdmins(admin);
        return ResponseEntity.ok(updatedAdmin);
    }
    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin with ID " + id + " has been deleted.";
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        AdminDTO adminDTO = adminService.getAdminById(id);
        return ResponseEntity.ok(adminDTO);
    }

}