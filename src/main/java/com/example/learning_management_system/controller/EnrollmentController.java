package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.requestDTO.EnrollmentRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EnrollmentResponseDTO;
import com.example.learning_management_system.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/")
    public EnrollmentResponseDTO enroll(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO){
        return enrollmentService.enroll(enrollmentRequestDTO);
    }

}
