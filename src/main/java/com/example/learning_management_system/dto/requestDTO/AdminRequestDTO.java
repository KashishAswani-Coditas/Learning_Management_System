package com.example.learning_management_system.dto.requestDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$", message = "Password should contain minimum eight and maximum 12 characters, at least one uppercase letter, one lowercase letter, one number and one special character")
    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, max = 12, message = "Password length should be minimum 8 chars and maximum 12")
    private String password;
}
