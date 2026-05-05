package com.example.learning_management_system.dto.responseDTO;

import com.example.learning_management_system.enums.ProgressStatus;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleProgressResponseDTO {

    private String id;
    private String moduleId;
    private String employeeId;
    private ProgressStatus status;
}
