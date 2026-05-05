package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.responseDTO.CertificateResponseDTO;
import com.example.learning_management_system.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CertificateMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "course.id", target = "courseId")
    CertificateResponseDTO toDto(Certificate certificate);
}