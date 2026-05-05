package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.entity.Course;
import com.example.learning_management_system.exception.CourseNotFoundException;
import com.example.learning_management_system.mapper.CourseMapper;
import com.example.learning_management_system.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseResponseDTO addCourse(CourseRequestDTO courseDto) {
        Course originalCourse = courseMapper.toEntity(courseDto);
        originalCourse.setId(UUID.randomUUID().toString());
        return courseMapper.toDto(courseRepository.save(originalCourse));
    }

    public CourseResponseDTO getCourse(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course Not Found Exception"));

        return courseMapper.toDto(course);
    }


}
