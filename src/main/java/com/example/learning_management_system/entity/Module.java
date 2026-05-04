package com.example.learning_management_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "module_name")
    private String moduleName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ElementCollection
    @CollectionTable(name = "module_youtube_links", joinColumns = @JoinColumn(name = "module_id"))
    @Column(name = "youtube_links")
    private List<String> youtubeLinks;

    @Column(name = "completion_status")
    private boolean completionStatus;


}
