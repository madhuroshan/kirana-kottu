package com.kiranakottu.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDTO {
    private String username;
    private String name;
    private String email;
    private String message; // e.g., "User registered successfully"
}