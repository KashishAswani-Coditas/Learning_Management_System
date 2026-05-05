package com.example.learning_management_system.dto.responseDTO;


import com.example.learning_management_system.enums.AssessmentStatus;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentResponseDTO {
    private String id;
    private AssessmentStatus status;
}
