package com.example.learning_management_system.security;

import com.example.learning_management_system.entity.Admin;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.repository.AdminRepository;
import com.example.learning_management_system.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin != null) {
            return new CustomUserDetails(admin.getEmail(), admin.getPassword(), "ADMIN");
        }

        Employee emp = employeeRepository.findByEmail(email).orElse(null);
        if (emp != null) {
            return new CustomUserDetails(emp.getEmail(), emp.getPassword(), "EMPLOYEE");
        }

        throw new UsernameNotFoundException("User not found");
    }
}
