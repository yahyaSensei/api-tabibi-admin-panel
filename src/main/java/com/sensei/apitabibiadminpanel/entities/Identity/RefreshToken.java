package com.sensei.apitabibiadminpanel.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "RefreshTokens", schema = "[identity]")
@Getter @Setter @NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Token", nullable = false, length = 1000)
    private String token;

    @Column(name = "ExpiresAtUtc", nullable = false)
    private LocalDateTime expiresAtUtc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private ApplicationUser user;
}