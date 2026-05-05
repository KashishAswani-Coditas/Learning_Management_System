package com.example.learning_management_system.service;


import com.example.learning_management_system.dto.requestDTO.EmployeeRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EmployeeResponseDTO;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.mapper.EmployeeMapper;
import com.example.learning_management_system.repository.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

//    public EmployeeResponseDTO addEmployee(@Valid EmployeeRequestDTO employeeDto) {
//        Employee employee = employeeMapper.toEntity(employeeDto);
//        return employeeMapper.toDto(employeeRepository.save(employee));
//    }

    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeDto) {

        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setId(UUID.randomUUID().toString());

        if (employeeDto.getManagerId() != null && !employeeDto.getManagerId().isBlank()) {
            Employee manager = employeeRepository.findById(employeeDto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));

            employee.setManager(manager);
        }

        employee.setId(java.util.UUID.randomUUID().toString());

        return employeeMapper.toDto(employeeRepository.save(employee));
    }
}
