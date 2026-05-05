package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    Boolean existsByEmployeeIdAndCourseId(String employeeId, String courseId);
    Optional<Enrollment> findByEmployeeIdAndCourseId(String employeeId, String courseId);


    List<Enrollment> findByEmployeeId(String employeeId);
}
