package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.ModuleProgress;
import com.example.learning_management_system.enums.ProgressStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, String> {
    Optional<ModuleProgress> findByEmployeeIdAndModuleId(String employeeId, String moduleId);

    long countByEmployeeIdAndModule_Course_IdAndStatus(String employeeId, String courseId, ProgressStatus status);

    List<ModuleProgress> findByEmployeeIdAndModule_Course_Id(String employeeId, String courseId);
}
