package com.example.learning_management_system.mapper;


import com.example.learning_management_system.dto.requestDTO.EmployeeRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EmployeeResponseDTO;
import com.example.learning_management_system.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO toDto(Employee  employee);
}
