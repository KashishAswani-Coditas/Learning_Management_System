package com.example.learning_management_system.dto.requestDTO;

import com.example.learning_management_system.enums.ProgressStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModuleProgressRequestDTO {
    private String moduleId;
    private ProgressStatus status;
}
