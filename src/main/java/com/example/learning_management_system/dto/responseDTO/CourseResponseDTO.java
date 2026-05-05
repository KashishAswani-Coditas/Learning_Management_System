package com.example.learning_management_system.dto.responseDTO;


import lombok.*;

import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private String name;
    private String description;
    private Long daysRequired;

    private List<ModuleResponseDTO> modules;
}
