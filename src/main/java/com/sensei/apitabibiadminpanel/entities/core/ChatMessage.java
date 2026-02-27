package com.sensei.apitabibiadminpanel.entities.core;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ChatMessages", schema = "core")
@Getter @Setter @NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "SenderId", nullable = false, length = 450)
    private String senderId;

    @Column(name = "ReceiverId", nullable = false, length = 450)
    private String receiverId;

    @Column(name = "Message", nullable = false, length = 1000)
    private String message;

    @Column(name = "SentAt", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "IsRead", nullable = false)
    private Boolean isRead;
}