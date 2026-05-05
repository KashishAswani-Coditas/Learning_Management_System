package com.example.learning_management_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "certificate")
public class Certificate {

    @Id
    private String id;

    //belongs to which employee
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //belongs to which course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
