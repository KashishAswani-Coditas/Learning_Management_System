package com.example.learning_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "days_required")
    private Long daysRequired;

    @OneToMany(mappedBy = "course")
    private List<Module> modules;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}
