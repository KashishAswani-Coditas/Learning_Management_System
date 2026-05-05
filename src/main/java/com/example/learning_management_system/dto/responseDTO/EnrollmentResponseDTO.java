package com.example.learning_management_system.dto.responseDTO;

import com.example.learning_management_system.enums.ProgressStatus;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private String id;
    private String employeeId;
    private String courseId;
    private ProgressStatus status;
}
