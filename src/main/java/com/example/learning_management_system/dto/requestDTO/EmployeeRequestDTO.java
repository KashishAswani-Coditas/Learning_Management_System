package com.example.learning_management_system.dto.requestDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "Password should contain minimum eight and maximum 12 characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 12, message = "Password length should be minimum 8 chars and maximum 12")
    private String password;

    private String managerId;

    private LocalDate hireDate;

    private boolean onBench;
}
