package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.requestDTO.ManagerEmployeeViewDTO;
import com.example.learning_management_system.dto.requestDTO.ManagerFilterDTO;
import com.example.learning_management_system.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/employee/{id}")
    public ManagerEmployeeViewDTO getEmployee(@PathVariable String id){
        return managerService.getEmployee(id);
    }

    @GetMapping("/subordinates")
    public List<ManagerEmployeeViewDTO> getSubordinates(){
        return managerService.getAllSubordinates();
    }

    @PostMapping("/dashboard/filter")
    public List<ManagerEmployeeViewDTO> filterEmployee(@RequestBody ManagerFilterDTO filterDTO){
        return managerService.filterEmployees(filterDTO);
    }
}
