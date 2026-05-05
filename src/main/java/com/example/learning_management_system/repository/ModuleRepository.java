package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, String> {
    List<CourseModule> findByCourseId(String id);
}
