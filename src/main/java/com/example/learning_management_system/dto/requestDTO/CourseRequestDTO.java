package com.example.learning_management_system.dto.requestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course Name can't be blank")
    @Size(min = 10, message = "Course name should be of minimum 10 characters")
    private String name;

    @NotBlank(message = "Description can't be blank")
    @Size(min = 15, message = "Description can't be blank should be of minimum 15 characters")
    private String description;

    private Long daysRequired;

}
