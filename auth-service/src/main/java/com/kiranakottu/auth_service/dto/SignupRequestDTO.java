package com.kiranakottu.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String username;
    private String name;
    private String email;
    private String password;
}
