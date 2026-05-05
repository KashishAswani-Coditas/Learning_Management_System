package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findByManagerId(String managerID);

}
