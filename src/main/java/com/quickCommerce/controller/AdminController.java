package com.quickCommerce.controller;


import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.entity.Admin;
import com.quickCommerce.service.AdminService;
import com.quickCommerce.dto.AdminDTO;
import com.quickCommerce.entity.Admin;
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
    public ResponseEntity<String> sendOtp(@RequestParam String username, @RequestParam String email) {
        String message = adminService.sendOtpToEmail(username, email);
        return ResponseEntity.ok(message);
    }

    // OTP verify Password set API
    @PostMapping("/verify-otp")
    public ResponseEntity<AdminDTO> verifyOtpAndSetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String password
    ) {
        AdminDTO adminDTO = adminService.verifyOtpAndAddPassword(email, otp,password);
        return ResponseEntity.ok(adminDTO);
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
    public AdminDTO getAdmin(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }
}