package com.kiranakottu.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private String name;
    private String message; // e.g., "Login successful"
}
