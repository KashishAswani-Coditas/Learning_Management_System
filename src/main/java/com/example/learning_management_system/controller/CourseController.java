package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRe

    @PostMapping("/add-course")
    public CourseResponseDTO addCourse(CourseRequestDTO course){
        return
    }
}
