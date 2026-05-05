package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.entity.CourseModule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleResponseDTO toDto(CourseModule courseModule);
    CourseModule toEntity(ModuleResponseDTO moduleResponseDTO);
}
