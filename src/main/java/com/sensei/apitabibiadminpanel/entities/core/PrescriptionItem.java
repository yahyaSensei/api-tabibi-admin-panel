package com.sensei.apitabibiadminpanel.entities.core;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PrescriptionItems", schema = "core")
@Getter @Setter @NoArgsConstructor
public class PrescriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "MedicineName", nullable = false, length = 200)
    private String medicineName;

    @Column(name = "Dosage", nullable = false, length = 100)
    private String dosage;

    @Column(name = "Frequency", nullable = false, length = 100)
    private String frequency;

    @Column(name = "Duration", nullable = false, length = 100)
    private String duration;

    @Column(name = "Instructions", nullable = false, length = 100)
    private String instructions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PrescriptionId", nullable = false)
    private Prescription prescription;
}