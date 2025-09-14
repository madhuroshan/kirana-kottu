package com.kiranakottu.auth_service.controller;

import com.kiranakottu.auth_service.dto.SignupRequestDTO;
import com.kiranakottu.auth_service.dto.SignupResponseDTO;
import com.kiranakottu.auth_service.entity.User;
import com.kiranakottu.auth_service.service.AuthService;
import com.kiranakottu.auth_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        try {
            User user = userService.registerUser(signupRequestDTO);
            SignupResponseDTO responseDTO = new SignupResponseDTO(
                    user.getUsername(),
                    user.getName(),
                    user.getEmail(),
                    "Customer created successfully"
            );

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SignupResponseDTO(
                    null,
                    null,
                    null,
                    e.getMessage()
            ));
        }
    }


}
