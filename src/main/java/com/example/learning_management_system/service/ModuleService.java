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

        if (moduleRepository.existsByModuleNameAndCourseId(moduleRequestDTO.getModuleName(), courseId)){
            throw new RuntimeException("Module in this course already exists");
        }

        CourseModule module = CourseModule.builder()
                .id(UUID.randomUUID().toString())
                .moduleName(moduleRequestDTO.getModuleName())
                .course(course)
                .youtubeLinks(moduleRequestDTO.getYoutubeLinks())
                .build();

        moduleRepository.save(module);

        return moduleMapper.toDto(module);
    }

    public ModuleResponseDTO updateModule(String moduleId, ModuleRequestDTO moduleRequestDTO) {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module Not Found"));

        if(!module.getModuleName().equals(moduleRequestDTO.getModuleName())
        && moduleRepository.existsByModuleNameAndCourseId(moduleRequestDTO.getModuleName(), module.getCourse().getId())){
            throw new RuntimeException("Module already exits!");
        }

        if(moduleRequestDTO.getModuleName() != null){
            module.setModuleName(moduleRequestDTO.getModuleName());
        }
        if(moduleRequestDTO.getYoutubeLinks() != null){
            module.setYoutubeLinks(moduleRequestDTO.getYoutubeLinks());
        }

        return moduleMapper.toDto(moduleRepository.save(module));
    }
}
