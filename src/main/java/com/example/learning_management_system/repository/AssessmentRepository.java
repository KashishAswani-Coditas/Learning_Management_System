package com.example.learning_management_system.repository;


import com.example.learning_management_system.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, String > {
    Optional<Assessment> findByEnrollmentId(String enrollmentId);
}
