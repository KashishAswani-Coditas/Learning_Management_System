package com.example.learning_management_system.dto.requestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnrollmentRequestDTO {

    @NotBlank(message = "Employee id can't be blank")
    private String employeeId;

    @NotBlank(message = "Course id can't be blank")
    private String courseId;
}
