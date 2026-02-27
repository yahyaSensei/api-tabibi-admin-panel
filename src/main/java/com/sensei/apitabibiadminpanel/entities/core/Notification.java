package com.sensei.apitabibiadminpanel.entities.core;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Notifications", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "UserId", nullable = false)
    private String userId;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "Message", nullable = false, length = 500)
    private String message;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "IsRead", nullable = false)
    private Boolean isRead;

    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "RelatedEntityId")
    private UUID relatedEntityId;
}