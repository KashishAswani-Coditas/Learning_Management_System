package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.requestDTO.ModuleProgressRequestDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleProgressResponseDTO;
import com.example.learning_management_system.entity.CourseModule;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.entity.ModuleProgress;
import com.example.learning_management_system.enums.ProgressStatus;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import com.example.learning_management_system.repository.ModuleProgressRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleProgressService {
    private final ModuleProgressRepository moduleProgressRepository;
    private final ModuleRepository moduleRepository;
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;


    //update progress
    public ModuleProgressResponseDTO updateProgress(ModuleProgressRequestDTO requestDTO) {
        //get the user who is logged in..
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));


        //fetch the module whose progress is supposed to be updated
        CourseModule module = moduleRepository.findById(requestDTO.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module does not exist"));

        //check if the enrollment exists
        Enrollment enrollment = enrollmentRepository.findByEmployeeIdAndCourseId(employee.getId(), module.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Not enrolled in this course"));

        //module progress
        ModuleProgress progress = moduleProgressRepository.findByEmployeeIdAndModuleId(employee.getId(), module.getId())
                .orElse(null);

        //if module progress is null -> create new progress
        if(progress == null){
            progress = ModuleProgress.builder()
                    .id(UUID.randomUUID().toString())
                    .employee(employee)
                    .module(module)
                    .build();
        }
        //or else just update the status
        progress.setStatus(requestDTO.getStatus());


        progress = moduleProgressRepository.save(progress);

//        update the enrollment status as per progress
        updateEnrollmentStatus(employee.getId(), module.getCourse().getId());

        return ModuleProgressResponseDTO.builder()
                .id(progress.getId())
                .moduleId(module.getId())
                .employeeId(employee.getId())
                .status(progress.getStatus())
                .build();
    }



//    updating enrollment status....
    private void updateEnrollmentStatus(String employeeId, String courseId){
        List<CourseModule> modules = moduleRepository.findByCourseId(courseId);
        long totalModules = modules.size();

        long completedModules = moduleProgressRepository.countByEmployeeIdAndModule_Course_IdAndStatus(employeeId, courseId, ProgressStatus.COMPLETED);

        Enrollment enrollment = enrollmentRepository.findByEmployeeIdAndCourseId(employeeId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment Not Found!!!!!"));

        if(completedModules == 0){
            enrollment.setStatus(ProgressStatus.NOT_STARTED);
        }else if (completedModules < totalModules){
            enrollment.setStatus(ProgressStatus.IN_PROGRESS);
        }else {
            enrollment.setStatus(ProgressStatus.COMPLETED);
            enrollment.setEndDate(LocalDateTime.now());
        }


        enrollmentRepository.save(enrollment);
    }
}