package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.requestDTO.AssessmentUpdateDTO;
import com.example.learning_management_system.dto.responseDTO.AssessmentResponseDTO;
import com.example.learning_management_system.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessment")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;

    @PostMapping("/create/{courseID}")
    public AssessmentResponseDTO createAssessment(@PathVariable String courseID){
        return assessmentService.createAssessment(courseID);
    }

    @PutMapping("/{id}")
    private AssessmentResponseDTO updateAssessment(@PathVariable String id, @RequestBody AssessmentUpdateDTO updateDTO){
        return assessmentService.updateAssessment(id, updateDTO);
    }
}
