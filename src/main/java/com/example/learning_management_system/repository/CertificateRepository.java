package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, String> {
    Optional<Certificate> findByEmployeeAndCourseId(String employeeId, String courseId);

    List<Certificate> findByEmployeeId(String employeeId);
}
