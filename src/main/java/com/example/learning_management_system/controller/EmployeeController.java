package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @GetMapping("/my-courses")
    public List<CourseResponseDTO> getMyCourses(){
        return new ArrayList<>();
    }
}
