package com.sensei.apitabibiadminpanel.entities.core;

import com.sensei.apitabibiadminpanel.entities.Identity.ApplicationUser;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Reviews", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Comment", length = 500)
    private String comment;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoctorId", nullable = false)
    private ApplicationUser doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = false)
    private ApplicationUser patient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookingId", nullable = false, unique = true)
    private Booking booking;
}