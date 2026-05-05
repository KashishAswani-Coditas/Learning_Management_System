package com.example.learning_management_system.service;


import com.example.learning_management_system.dto.requestDTO.ManagerEmployeeViewDTO;
import com.example.learning_management_system.dto.requestDTO.ManagerFilterDTO;
import com.example.learning_management_system.entity.Employee;
import com.example.learning_management_system.entity.Enrollment;
import com.example.learning_management_system.mapper.ManagerMapper;
import com.example.learning_management_system.repository.EmployeeRepository;
import com.example.learning_management_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private  final ManagerMapper mapper;

//    public List<ManagerEmployeeViewDTO> filterDashboard(ManagerFilterDTO filter){
//        Employee manager = getLoggedManager();
//
//        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());
//
//        return employees.stream()
//                .filter(employee -> filter.getOnBench() == null|| employee.isOnBench() == filter.getOnBench())
//                .map(employee -> )
//    }

    public ManagerEmployeeViewDTO getEmployee(String employeeId) {
        Employee manager = getLoggedManager();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getManager() == null || !employee.getManager().getId().equals(manager.getId())) {
            throw new RuntimeException("Unauthorised access..");
        }

        List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(employee.getId());

        Enrollment latest = enrollments.isEmpty() ? null : enrollments.get(enrollments.size()-1);

        return mapper.toDTO(employee, latest);

    }

//    //get all subordinates
//    public List<ManagerEmployeeViewDTO> getAllSubordinates(){
//        Employee manager = getLoggedManager();
//
//        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());
//
//    }

    private Employee getLoggedManager(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Manager Not Found!!"));
    }


}