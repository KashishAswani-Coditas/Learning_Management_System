package com.example.learning_management_system.controller;


import com.example.learning_management_system.dto.requestDTO.ModuleProgressRequestDTO;
import com.example.learning_management_system.dto.responseDTO.ModuleProgressResponseDTO;
import com.example.learning_management_system.service.ModuleProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module-progress")
@RequiredArgsConstructor
public class ModuleProgressController {
    private final ModuleProgressService moduleProgressService;

    @PostMapping
    public ModuleProgressResponseDTO updateProgress(@RequestBody ModuleProgressRequestDTO progressRequestDTO){
        return moduleProgressService.updateProgress(progressRequestDTO);
    }
}
