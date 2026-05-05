package com.example.learning_management_system.service;


import com.example.learning_management_system.dto.responseDTO.CertificateResponseDTO;
import com.example.learning_management_system.entity.Assessment;
import com.example.learning_management_system.entity.Certificate;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.enums.AssessmentStatus;
import com.example.learning_management_system.enums.ProgressStatus;
import com.example.learning_management_system.mapper.CertificateMapper;
import com.example.learning_management_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssessmentRepository assessmentRepository;
    private final CertificateMapper certificateMapper;

    public CertificateResponseDTO generateCertificate(String courseId){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        //check if employee exist

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee Not Found!!!"));

        Enrollment enrollment = enrollmentRepository.findByEmployeeIdAndCourseId(employee.getId(), courseId)
                .orElseThrow(()-> new RuntimeException("Not Enrolled!!"));

        if(enrollment.getStatus() != ProgressStatus.COMPLETED){
            throw new RuntimeException("Course not completed yet..");
        }

        Assessment assessment = assessmentRepository
                .findByEnrollmentId(enrollment.getId())
                .orElseThrow(() -> new RuntimeException("Assessment not found!!!"));

        if(assessment.getAssessmentStatus() != AssessmentStatus.PASS){
            throw new RuntimeException("Assessment not cleared!!");
        }

        //check if already there
        Optional<Certificate> existing = certificateRepository.findByEmployeeAndCourseId(employee.getId(), courseId);

        if(existing.isPresent()){
            Certificate certificate = existing.get();
            return CertificateResponseDTO.builder()
                    .courseId(certificate.getCourse().getId())
                    .employeeId(certificate.getEmployee().getId())
                    .issueDate(certificate.getIssueDate())
                    .build();
        }

        //create new
        Certificate certificate = Certificate.builder()
                .id(UUID.randomUUID().toString())
                .employee(employee)
                .course(enrollment.getCourse())
                .issueDate(LocalDateTime.now())
                .build();

        certificateRepository.save(certificate);

        return CertificateResponseDTO
                .builder()
                .courseId(courseId)
                .employeeId(certificate.getEmployee().getId())
                .issueDate(certificate.getIssueDate())
                .build();
    }

    public List<CertificateResponseDTO> getMyCertificates(){
        //get the employee first..
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not exists.."));

        return certificateRepository.findByEmployeeId(employee.getId())
                .stream()
                .map(certificateMapper::toDto)
                .toList();
    }

}
