package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.AdminRequestDTO;
import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.requestDTO.ModuleRequestDTO;
import com.example.learning_management_system.dto.responseDTO.AdminResponseDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.service.AdminService;
import com.example.learning_management_system.service.CourseService;
import com.example.learning_management_system.service.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;
    private final ModuleService moduleService;


    @PostMapping("/signup")
    public AdminResponseDTO addNewAdmin( @Valid @RequestBody AdminRequestDTO admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminService.addAdmin(admin);
    }


//    Course Controls

    @PostMapping("/add-course")
    public CourseResponseDTO addCourse(@Valid @RequestBody CourseRequestDTO course){
        return courseService.addCourse(course);
    }

    @PostMapping("/course/{courseId}/add-module")
    private ModuleResponseDTO addModule(@PathVariable String courseId, @RequestBody ModuleRequestDTO moduleRequestDTO){
        return moduleService.addModule(courseId, moduleRequestDTO);
    }
}
