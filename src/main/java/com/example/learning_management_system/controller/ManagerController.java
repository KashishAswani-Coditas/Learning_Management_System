package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.requestDTO.ManagerEmployeeViewDTO;
import com.example.learning_management_system.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/employee/{id}")
    public ManagerEmployeeViewDTO getEmployee(@PathVariable String id){
        return managerService.getEmployee(id);
    }
}
