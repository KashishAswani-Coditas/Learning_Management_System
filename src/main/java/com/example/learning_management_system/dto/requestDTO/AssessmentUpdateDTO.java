package com.example.learning_management_system.dto.requestDTO;

import com.example.learning_management_system.enums.AssessmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssessmentUpdateDTO {
    private AssessmentStatus status;
}
