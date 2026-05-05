package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleResponseDTO;
import com.example.learning_management_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

//    all courses
    @GetMapping("/all")
    public List<CourseResponseDTO> getAllCourse(){
        return courseService.getAllCourses();
    }

//    course with modules
    @GetMapping("/{id}")
    public CourseResponseDTO getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }

//    get modules of a course
    @GetMapping("/{id}/modules")
    public List<ModuleResponseDTO> getModules(@PathVariable String id){
        return courseService.getModulesByCourseId(id);
    }


//    get single module
    @GetMapping("/module/{moduleId}")
    public ModuleResponseDTO getModule(@PathVariable String moduleId){
        return courseService.getModule(moduleId);
    }
}
