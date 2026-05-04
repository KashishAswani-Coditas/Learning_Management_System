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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    //need to set the default value as Not_started
    @Column(name = "status")
    private ProgressStatus status;
}
