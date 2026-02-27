package com.sensei.apitabibiadminpanel.entities.core;

import com.sensei.apitabibiadminpanel.entities.Identity.ApplicationUser;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Favorites", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Favorite {

    @EmbeddedId
    private FavoriteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("patientId")
    @JoinColumn(name = "PatientId", nullable = false)
    private ApplicationUser patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "DoctorId", nullable = false)
    private ApplicationUser doctor;

    @Column(name = "AddedAt", nullable = false)
    private LocalDateTime addedAt;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    public static class FavoriteId implements Serializable {
        @Column(name = "PatientId", length = 450)
        private String patientId;

        @Column(name = "DoctorId", length = 450)
        private String doctorId;
    }
}