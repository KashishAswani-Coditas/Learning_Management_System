package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.ModuleProgressRequestDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleProgressResponseDTO;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import com.example.learning_management_system.repository.ModuleProgressRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleProgressService {
    private final ModuleProgressRepository moduleProgressRepository;
    private final ModuleRepository moduleRepository;
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;


    //update progress
    public ModuleProgressResponseDTO updateProgress(ModuleProgressRequestDTO requestDTO){
        //get the user who is logged in..
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();


    }
}
