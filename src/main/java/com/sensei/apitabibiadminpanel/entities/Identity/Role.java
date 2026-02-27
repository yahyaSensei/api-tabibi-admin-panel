package com.sensei.apitabibiadminpanel.entities.Identity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "AspNetRoles", schema = "[identity]")
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @Column(name = "Id", length = 450)
    private String id;

    @Column(name = "Name", length = 256)
    private String name;

    @Column(name = "NormalizedName", length = 256)
    private String normalizedName;

    @Column(name = "ConcurrencyStamp")
    private String concurrencyStamp;
}