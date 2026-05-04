package com.example.learning_management_system.mapper;


import com.example.learning_management_system.dto.requestDTO.AdminRequestDTO;
import com.example.learning_management_system.dto.responseDTO.AdminResponseDTO;
import com.example.learning_management_system.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminRequestDTO adminRequestDTO);
    AdminResponseDTO toDto(Admin admin);
}
