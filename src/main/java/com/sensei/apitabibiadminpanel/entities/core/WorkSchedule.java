package com.sensei.apitabibiadminpanel.entities.core;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "WorkSchedules", schema = "core")
@Getter @Setter @NoArgsConstructor
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "DayOfWeek", nullable = false)
    private Integer dayOfWeek;

    @Column(name = "OpenTime", nullable = false)
    private LocalTime openTime;

    @Column(name = "CloseTime", nullable = false)
    private LocalTime closeTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClinicId", nullable = false)
    private Clinic clinic;
}