package com.example.learning_management_system.dto.responseDTO;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {

    private String firstName;

    private String lastName;

    private String email;

    public static class ModuleResponseDTO {
    }
}
