package com.example.learning_management_system.dto.responseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ModuleResponseDTO {
    private String moduleName;
    private List<String> youtubeLinks;
}
