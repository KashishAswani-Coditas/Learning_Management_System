package com.example.learning_management_system.dto.responseDTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponseDTO {
    private String courseId;
    private String employeeId;
    private LocalDateTime issueDate;
}
