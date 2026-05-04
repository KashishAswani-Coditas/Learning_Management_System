package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.CourseRequestDTO;
import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add-course")
    public CourseResponseDTO addCourse(CourseRequestDTO course){
        return courseService.addCourse(course);
    }

    @GetMapping("/get-course/{id}")
    public CourseResponseDTO getCourse(@PathVariable String id){
        return courseService.getCourse(id);
    }




//    Delete course should be allowed or not??? cause may lead to inconsistency od data for those who have already completed...
//    Need to decide this
//    @DeleteMapping("/remove-course")
}
