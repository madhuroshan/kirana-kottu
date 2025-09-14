package com.kiranakottu.auth_service.service;

import com.kiranakottu.auth_service.dto.LoginResponseDTO;
import com.kiranakottu.auth_service.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO authenticate(String usernameOrEmail, String password) {
        // Authenticate user
        User user = userService.findByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String passwordHash = user.getPassword();
        boolean match = passwordEncoder.matches(password, passwordHash);
        if (!match) {
            throw new RuntimeException("Invalid credentials");
        }

        return new LoginResponseDTO(
                user.getName(),
                "Login successful"
        );
    }

}
