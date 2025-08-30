package com.springprojects.to_do.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("admin");
        boolean isValidPassword = password.equalsIgnoreCase("password");
        return isValidUsername & isValidPassword;
    }
}
