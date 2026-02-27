package com.sensei.apitabibiadminpanel.entities.shared;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Departments", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Name", nullable = false, length = 200)
    private String name;

    @Column(name = "Description", length = 1000)
    private String description;

    @Column(name = "ImageUrl", length = 300)
    private String imageUrl;

    @Column(name = "CreatedAtUtc", nullable = false)
    private LocalDateTime createdAtUtc;
}