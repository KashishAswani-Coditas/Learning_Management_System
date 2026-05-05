package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.LoginRequestDTO;
import com.example.learning_management_system.dto.responseDTO.LoginResponseDTO;
import com.example.learning_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}