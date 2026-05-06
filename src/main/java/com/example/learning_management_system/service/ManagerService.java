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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ManagerMapper mapper;


    public ManagerEmployeeViewDTO getEmployee(String employeeId) {
        Employee manager = getLoggedManager();

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getManager() == null || !employee.getManager().getId().equals(manager.getId())) {
            throw new RuntimeException("Unauthorised access..");
        }

        List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(employee.getId());

        Enrollment latest = enrollments.isEmpty() ? null : enrollments.get(enrollments.size() - 1);

        return mapper.toDTO(employee, latest);

    }

    //get all subordinates
    public List<ManagerEmployeeViewDTO> getAllSubordinates() {
        Employee manager = getLoggedManager();

        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());

        List<ManagerEmployeeViewDTO> result = new ArrayList<>();

        for (Employee e : employees) {
            List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(e.getId());

            Enrollment latest = null;
            if (!enrollments.isEmpty()) {
                latest = enrollments.get(enrollments.size() - 1);
            }

            ManagerEmployeeViewDTO dto = mapper.toDTO(e, latest);
            result.add(dto);
        }

        return result;

    }

    public List<ManagerEmployeeViewDTO> filterEmployees(ManagerFilterDTO filterDTO) {
        Employee manager = getLoggedManager();

        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());

        List<ManagerEmployeeViewDTO> result = new ArrayList<>();

        for (Employee employee : employees) {
            List<Enrollment> enrollments = enrollmentRepository.findByEmployeeId(employee.getId());

            Enrollment latestEnrollment = enrollments.isEmpty() ? null : enrollments.get(enrollments.size() - 1);

            ManagerEmployeeViewDTO viewDTO = mapper.toDTO(employee, latestEnrollment);

            boolean matches =
                    (filterDTO.getOnBench() == null ||
                            viewDTO.isOnBench() == filterDTO.getOnBench())

                            &&

                            (filterDTO.getEnrolled() == null ||
                                    viewDTO.isEnrolled() == filterDTO.getEnrolled())

                            &&

                            (filterDTO.getCourseId() == null ||
                                    filterDTO.getCourseId().equals(viewDTO.getCourseId()))

                            &&

                            (filterDTO.getStatus() == null ||
                                    filterDTO.getStatus() == viewDTO.getStatus());

            if (matches) {
                result.add(viewDTO);
            }
        }

        return result;
    }

    private Employee getLoggedManager() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Manager Not Found!!"));
    }
}