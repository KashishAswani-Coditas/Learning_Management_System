package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.responseDTO.CourseResponseDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleProgressResponseDTO;
import com.example.learning_management_system.entity.Course;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.mapper.CourseMapper;
import com.example.learning_management_system.repository.EnrollmentRepository;
import com.example.learning_management_system.service.ModuleProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseMapper courseMapper;
    private final ModuleProgressService moduleProgressService;

    @GetMapping("/my-courses")
    public List<CourseResponseDTO> getMyCourses(Authentication authentication){

        Employee employee = (Employee) authentication.getPrincipal();

        List<Enrollment> enrollments =
                enrollmentRepository.findByEmployee(employee);

        List<Course> courses = enrollments.stream()
                .map(Enrollment::getCourse)
                .toList();

        return courses.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

//    @GetMapping("/my-progress/{courseId}")
//    public List<ModuleProgressResponseDTO> getMyProgress(
//            @PathVariable String courseId,
//            Authentication authentication
//    ){
//
//        return progressService.getCourseProgress(
//                authentication.getName(),
//                courseId
//        );
//    }

}
