package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.requestDTO.ManagerEmployeeViewDTO;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    public ManagerEmployeeViewDTO toDTO(Employee emp, Enrollment enrollment) {
        return ManagerEmployeeViewDTO.builder()
                .employeeId(emp.getId())
                .name(emp.getFirstName() + " " + emp.getLastName())
                .onBench(emp.isOnBench())
                .enrolled(enrollment != null)
                .courseId(enrollment != null ? enrollment.getCourse().getId() : null)
                .status(enrollment != null ? enrollment.getStatus() : null)
                .build();
    }
}