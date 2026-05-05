package com.example.learning_management_system.dto.requestDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {

    @NotBlank(message = "First name can't be empty")
    private String firstName;

    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 12, message = "Password length should be between 8-12 char")
    private String password;

    private String managerId;

    private LocalDate hireDate;

    private boolean onBench;
}
