package com.sensei.apitabibiadminpanel.entities.shared;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "Cities", schema = "core")
@Getter @Setter @NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;
}