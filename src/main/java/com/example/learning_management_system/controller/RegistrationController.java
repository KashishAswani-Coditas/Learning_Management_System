package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.requestDTO.EmployeeRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EmployeeResponseDTO;
import com.example.learning_management_system.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/add-employee")
    public EmployeeResponseDTO addNewEmployee(@Valid @RequestBody EmployeeRequestDTO employee){
        employee.setHireDate(LocalDate.now());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return registrationService.addEmployee(employee);
    }
}
