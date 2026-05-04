package com.example.learning_management_system.entity;

import com.example.learning_management_system.enums.AssessmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assessment")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id" )
    private Enrollment enrollment;

    @Enumerated(EnumType.STRING)
    private AssessmentStatus assessmentStatus;
}
