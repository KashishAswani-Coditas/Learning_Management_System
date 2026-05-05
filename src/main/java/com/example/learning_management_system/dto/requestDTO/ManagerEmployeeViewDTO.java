package com.example.learning_management_system.dto.requestDTO;

import com.example.learning_management_system.enums.ProgressStatus;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerEmployeeViewDTO {
    private String employeeId;
    private String name;
    private boolean onBench;
    private  boolean enrolled;
    private String courseId;
    private ProgressStatus status;
}
