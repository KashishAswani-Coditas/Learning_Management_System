package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.requestDTO.EnrollmentRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EnrollmentResponseDTO;
import com.example.learning_management_system.entity.Enrollment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentResponseDTO toDto(Enrollment enrollment);
    Enrollment toEntity(EnrollmentRequestDTO enrollmentRequestDTO);
}
