package com.sensei.apitabibiadminpanel.entities.core;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Prescriptions", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Diagnosis", nullable = false, length = 500)
    private String diagnosis;

    @Column(name = "Notes", length = 1000)
    private String notes;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookingId", nullable = false, unique = true)
    private Booking booking;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionItem> items;
}