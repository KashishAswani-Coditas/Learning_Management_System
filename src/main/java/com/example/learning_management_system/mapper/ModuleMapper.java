package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.entity.CourseModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

//    @Mapping(source = "youtubeLink", target = "youtubeLinks")
    ModuleResponseDTO toDto(CourseModule courseModule);
    CourseModule toEntity(ModuleResponseDTO moduleResponseDTO);
}
