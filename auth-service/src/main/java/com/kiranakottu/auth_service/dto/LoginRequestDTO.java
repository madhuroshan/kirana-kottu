package com.kiranakottu.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String usernameOrEmail;
    private String password;
}
