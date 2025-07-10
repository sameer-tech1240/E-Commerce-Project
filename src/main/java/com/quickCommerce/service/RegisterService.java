package com.quickCommerce.service;

import com.quickCommerce.dto.DeliveryBoyDTO;

public interface RegisterService {
    String registerDeliveryBoy(DeliveryBoyDTO dto);
    String verifyOtp(String email, String otp);
}
