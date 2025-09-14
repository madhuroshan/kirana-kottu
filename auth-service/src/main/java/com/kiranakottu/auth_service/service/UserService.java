package com.kiranakottu.auth_service.service;

import com.kiranakottu.auth_service.dto.SignupRequestDTO;
import com.kiranakottu.auth_service.entity.Role;
import com.kiranakottu.auth_service.entity.User;
import com.kiranakottu.auth_service.repository.RoleRepository;
import com.kiranakottu.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequestDTO signupRequestDTO) {
        if (userRepository.existsByUsername(signupRequestDTO.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        if (userRepository.existsByEmail(signupRequestDTO.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        Role customerRole = roleRepository.findByName("CUSTOMER");

        User user = new User();
        user.setUsername(signupRequestDTO.getUsername());
        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setRole(customerRole); // Default role

        return userRepository.save(user);// In real applications, hash the password
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }


}
