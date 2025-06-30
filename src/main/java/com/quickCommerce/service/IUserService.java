package com.quickCommerce.service;


import com.quickCommerce.entity.User;

public interface IUserService {
    String register(User user);

    String verifyOtp(String email, String otp);

    String login(User user);
}
