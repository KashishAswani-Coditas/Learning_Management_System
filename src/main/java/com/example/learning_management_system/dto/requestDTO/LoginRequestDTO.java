package com.example.learning_management_system.dto.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class LoginRequestDTO {

    @Email(message = "Invalid email format, please try again")
    private String email;

    @NotBlank(message = "Password field can't be blank")
    private String password;
}
