package com.example.learning_management_system.service;


import com.example.learning_management_system.dto.requestDTO.AssessmentUpdateDTO;
import com.example.learning_management_system.dto.responseDTO.AssessmentResponseDTO;
import com.example.learning_management_system.entity.Assessment;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.enums.AssessmentStatus;
import com.example.learning_management_system.enums.ProgressStatus;
import com.example.learning_management_system.repository.AdminRepository;
import com.example.learning_management_system.repository.AssessmentRepository;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;


    public AssessmentResponseDTO createAssessment(String courseId){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found!!!"));

        Enrollment enrollment = enrollmentRepository.findByEmployeeIdAndCourseId(employee.getId(), courseId)
                .orElseThrow(() -> new RuntimeException("Not Enrolled"));

        if(enrollment.getStatus() != ProgressStatus.COMPLETED){
            throw new RuntimeException("Course not completed!!!");
        }

        //check if assessment already done
        if(assessmentRepository.findByEnrollmentId(enrollment.getId()).isPresent()){
            throw new RuntimeException("Assessment already exists...");
        }

        //create assessment..

        Assessment assessment = Assessment.builder()
                .id(UUID.randomUUID().toString())
                .enrollment(enrollment)
                .assessmentStatus(AssessmentStatus.NOT_ATTEMPTED)
                .build();

        assessmentRepository.save(assessment);

        return AssessmentResponseDTO.builder()
                .id(assessment.getId())
                .status(assessment.getAssessmentStatus())
                .build();
    }


    //update assessment status...
    public AssessmentResponseDTO updateAssessment(String id, AssessmentUpdateDTO dto){

        //check if the user is admin..

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Only Admin ALLOWED!!"));


        //load the assesment
        Assessment assessment = assessmentRepository.findByEnrollmentId(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found.."));

        assessment.setAssessmentStatus(dto.getStatus());

        assessmentRepository.save(assessment);

        return AssessmentResponseDTO.builder()
                .id(assessment.getId())
                .status(assessment.getAssessmentStatus())
                .build();
    }
}
