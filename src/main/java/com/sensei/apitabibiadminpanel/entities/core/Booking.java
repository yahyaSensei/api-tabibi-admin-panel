package com.sensei.apitabibiadminpanel.entities.core;

import com.sensei.apitabibiadminpanel.entities.Identity.ApplicationUser;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Bookings", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "AppointmentDate", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(name = "Status", nullable = false)
    private Integer status;

    @Column(name = "Type", nullable = false)
    private Integer type;

    @Column(name = "PricePaid", nullable = false, precision = 18, scale = 2)
    private BigDecimal pricePaid;

    @Column(name = "PaymentIntentId")
    private String paymentIntentId;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientId", nullable = false)
    private ApplicationUser patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoctorId", nullable = false)
    private ApplicationUser doctor;
}