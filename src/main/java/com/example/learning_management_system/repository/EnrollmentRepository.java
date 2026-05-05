package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    boolean existsByEmployeeIdAndCourseId(String employeeId, String courseId);
}
