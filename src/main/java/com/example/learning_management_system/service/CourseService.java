package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.entity.Course;
import com.example.learning_management_system.entity.CourseModule;
import com.example.learning_management_system.exception.CourseNotFoundException;
import com.example.learning_management_system.mapper.CourseMapper;
import com.example.learning_management_system.mapper.ModuleMapper;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public CourseResponseDTO addCourse(CourseRequestDTO courseDto) {
        Course originalCourse = courseMapper.toEntity(courseDto);
        if(courseRepository.existsByName(courseDto.getName())){
            throw new RuntimeException("Course already exists!!");
        }
        originalCourse.setId(UUID.randomUUID().toString());
        return courseMapper.toDto(courseRepository.save(originalCourse));
    }

    public CourseResponseDTO getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course Not Found Exception"));

        return courseMapper.toDto(course);
    }


    public CourseResponseDTO updateCourse(String id, @Valid CourseRequestDTO courseDto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));

        courseMapper.updateCourseFromDto(courseDto, course);

        return courseMapper.toDto(courseRepository.save(course));
    }

    public Page<CourseResponseDTO> getAllCourses(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        return courseRepository.findAll(pageable)
                .map(course -> {
                    CourseResponseDTO dto = courseMapper.toDto(course);
                    dto.setModules(null);
                    return dto;
                });
    }

    public List<ModuleResponseDTO> getModulesByCourseId(String id) {
        List<CourseModule> modules = moduleRepository.findByCourseId(id);

        return modules.stream()
                .map(moduleMapper::toDto)
                .toList();
    }

    public ModuleResponseDTO getModule(String moduleId) {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module does not exist"));

        return moduleMapper.toDto(module);
    }
}
