package com.example.learning_management_system.repository;

import com.example.learning_management_system.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
