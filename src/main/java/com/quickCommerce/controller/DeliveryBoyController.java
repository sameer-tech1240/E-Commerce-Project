package com.quickCommerce.controller;

import com.quickCommerce.dto.DeliveryBoyDTO;
import com.quickCommerce.dto.LoginDTO;
import com.quickCommerce.service.LoginService;
import com.quickCommerce.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryBoyController {

    private final RegisterService registerService;
    private final LoginService loginService;

    public DeliveryBoyController(RegisterService registerService, LoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DeliveryBoyDTO dto) {
        return ResponseEntity.ok(registerService.registerDeliveryBoy(dto));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return ResponseEntity.ok(registerService.verifyOtp(email, otp));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(loginService.login(loginDTO));
    }
}
