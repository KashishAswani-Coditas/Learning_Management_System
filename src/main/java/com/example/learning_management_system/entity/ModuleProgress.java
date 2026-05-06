package com.example.learning_management_system.entity;

import com.example.learning_management_system.enums.ProgressStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module_progress")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleProgress {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private CourseModule module;

    //need to set the default value as Not_started
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
}
