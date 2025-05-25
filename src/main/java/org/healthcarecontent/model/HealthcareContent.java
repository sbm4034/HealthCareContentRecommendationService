package org.healthcarecontent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "healthcare_content", schema = "org")
@Data
public class HealthcareContent {
    @Id
    private Long id;

    private String title;

    @Column(length = 2048)
    private String description;

    private String category;

    @Column(length = 1024)
    private String keywords;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "similarity_score")
    private Double similarityScore;

}