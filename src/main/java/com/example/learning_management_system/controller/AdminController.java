package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.AdminRequestDTO;
import com.example.learning_management_system.dto.responseDTO.AdminResponseDTO;
import com.example.learning_management_system.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    private AdminResponseDTO addNewAdmin(@RequestBody AdminRequestDTO admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminService.addAdmin(admin);
    }



}
