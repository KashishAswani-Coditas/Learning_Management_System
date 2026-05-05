package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.ModuleRequestDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.entity.Course;
import com.example.learning_management_system.entity.CourseModule;
import com.example.learning_management_system.mapper.ModuleMapper;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleResponseDTO addModule(String courseId, ModuleRequestDTO moduleRequestDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("Course Not Found Exception"));

        CourseModule module = CourseModule.builder()
                .id(UUID.randomUUID().toString())
                .moduleName(moduleRequestDTO.getModuleName())
                .course(course)
                .youtubeLink(moduleRequestDTO.getYoutubeLinks())
                .build();

        return moduleMapper.toDto(module);
    }

    public ModuleResponseDTO updateModule(String moduleId, ModuleRequestDTO moduleRequestDTO) {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module Not Found"));

        if(moduleRequestDTO.getModuleName() != null){
            module.setModuleName(moduleRequestDTO.getModuleName());
        }
        if(moduleRequestDTO.getYoutubeLinks() != null){
            module.setYoutubeLink(moduleRequestDTO.getYoutubeLinks());
        }

        return moduleMapper.toDto(moduleRepository.save(module));
    }
}
