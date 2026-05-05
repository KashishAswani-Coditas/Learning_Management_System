package com.example.learning_management_system.dto.responseDTO;


import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private boolean onBench;
}
