package com.example.learning_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "course")
public class Course {

    @Id
    private String id;

    private String name;

    private String description;

    @Column(name = "days_required")
    private Long daysRequired;

    @OneToMany(mappedBy = "course")
    private List<CourseModule> modules;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}
