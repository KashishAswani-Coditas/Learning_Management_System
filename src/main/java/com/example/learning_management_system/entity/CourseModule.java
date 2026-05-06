package com.example.learning_management_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "course_module")
public class CourseModule {

    @Id
    private String id;

    @Column(name = "module_name")
    private String moduleName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ElementCollection
    @CollectionTable(name = "module_youtube_links", joinColumns = @JoinColumn(name = "module_id"))
    @Column(name = "youtube_link")
    private List<String> youtubeLinks;

}
