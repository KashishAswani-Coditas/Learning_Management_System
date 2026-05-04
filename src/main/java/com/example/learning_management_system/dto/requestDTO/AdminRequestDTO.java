package com.example.learning_management_system.dto.requestDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDTO {

    @NotBlank(message = "First Name can't be blank")
    @Size(min = 3, message = "Minimum length should be 3")
    private String firstName;

    @NotBlank(message = "Last Name can't be blank")
    @Size(min = 3, message = "Minimum length should be 3")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 12, message = "Password length should be minimum 8 chars and maximum 12")
    private String password;
}
