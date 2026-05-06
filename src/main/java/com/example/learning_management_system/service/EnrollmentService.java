package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.EnrollmentRequestDTO;
import com.example.learning_management_system.dto.responseDTO.EnrollmentResponseDTO;
import com.example.learning_management_system.entity.Admin;
import com.example.learning_management_system.entity.Course;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.enums.ProgressStatus;
import com.example.learning_management_system.mapper.EnrollmentMapper;
import com.example.learning_management_system.repository.AdminRepository;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EmployeeRepository employeeRepository;
    private final CourseRepository courseRepository;
    private final AdminRepository adminRepository;
    private final EnrollmentMapper enrollmentMapper;


    //enroll
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO requestDTO){

        //get the user who is logged in
        String  email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        //check if the user is admin or employee

        Admin admin = adminRepository.findByEmail(email)
                .orElse(null);

        Employee loggedEmployee = employeeRepository.findByEmail(email)
                .orElse(null);


        //fetch the targeted employee
        Employee targetedEmployee = employeeRepository.findById(requestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        // authorization logic
        boolean isAllowed = false;


        if(admin != null){
            isAllowed = true;

        } else if (loggedEmployee != null){

            // employee enrolling himself
            if(loggedEmployee.getId().equals(targetedEmployee.getId())){
                isAllowed = true;

                // manager enrolling subordinate
            } else if (
                    targetedEmployee.getManager() != null &&
                            targetedEmployee.getManager().getId().equals(loggedEmployee.getId())
            ) {
                isAllowed = true;
            }
        }


        if(!isAllowed){
            throw new RuntimeException("Unauthorised enrollment attempt..");
        }

        //check if enrollment already exists
        if (enrollmentRepository.existsByEmployeeIdAndCourseId(targetedEmployee.getId(), requestDTO.getCourseId())){
            throw new RuntimeException("Enrollment already exists!!!");
        }

        //create enrollment

        //fetch the course
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course you are looking for does not exists"));

        Enrollment newEnrollment = Enrollment.builder()
                .id(UUID.randomUUID().toString())
                .employee(targetedEmployee)
                .course(course)
                .startDate(LocalDateTime.now())
                .status(ProgressStatus.NOT_STARTED)
                .build();

        return enrollmentMapper.toDto(enrollmentRepository.save(newEnrollment));
    }

}
