package com.example.learning_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "employee")
public class Employee {

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> subordinates;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "on_bench")
    private boolean onBench;

    @OneToMany(mappedBy = "employee")
    private List<Enrollment> enrollments;
}
