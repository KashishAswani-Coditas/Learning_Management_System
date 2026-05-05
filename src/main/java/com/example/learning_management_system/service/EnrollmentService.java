package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.EnrollmentRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EnrollmentResponseDTO;
import com.example.learning_management_system.entity.Admin;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.repository.AdminRepository;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;
    private final AdminRepository adminRepository;


    //enroll
    private EnrollmentResponseDTO enroll(EnrollmentRequestDTO requestDTO){

        //get the user who is logged in
        String  email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        //check if the user is admin or employee

        Admin admin = adminRepository.findByEmail(email)
                .orElse(null);

        Employee loggedEmployee = employeeRepository.findByEmail(email)
                .orElse(null);


        //fetch the targeted employee
        Employee targetedEmployee = employeeRepository.findById(requestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        // authorization logic
        boolean isAllowed = false;

    }
}
