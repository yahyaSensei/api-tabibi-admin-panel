package com.sensei.apitabibiadminpanel.entities.Identity;



import com.sensei.apitabibiadminpanel.entities.shared.City;
import com.sensei.apitabibiadminpanel.entities.shared.Department;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "AspNetUsers", schema = "[identity]")
@Getter @Setter @NoArgsConstructor
public class ApplicationUser {

    @Id
    @Column(name = "Id", length = 450)
    private String id;

    @Column(name = "Name", nullable = false, length = 300)
    private String name;

    @Column(name = "AvatarUrl", length = 500)
    private String avatarUrl;

    @Column(name = "Gender")
    private Integer gender;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "CreatedAtUtc", nullable = false)
    private LocalDateTime createdAtUtc;

    @Column(name = "UpdatedAtUtc")
    private LocalDateTime updatedAtUtc;

    @Column(name = "UserName", length = 256)
    private String userName;

    @Column(name = "NormalizedUserName", length = 256)
    private String normalizedUserName;

    @Column(name = "Email", length = 256)
    private String email;

    @Column(name = "NormalizedEmail", length = 256)
    private String normalizedEmail;

    @Column(name = "EmailConfirmed", nullable = false)
    private Boolean emailConfirmed;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Column(name = "SecurityStamp")
    private String securityStamp;

    @Column(name = "ConcurrencyStamp")
    private String concurrencyStamp;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PhoneNumberConfirmed", nullable = false)
    private Boolean phoneNumberConfirmed;

    @Column(name = "TwoFactorEnabled", nullable = false)
    private Boolean twoFactorEnabled;

    @Column(name = "LockoutEnd")
    private OffsetDateTime lockoutEnd;

    @Column(name = "LockoutEnabled", nullable = false)
    private Boolean lockoutEnabled;

    @Column(name = "AccessFailedCount", nullable = false)
    private Integer accessFailedCount;

    @Column(name = "Bio", length = 1000)
    private String bio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityId")
    private City city;

    @Column(name = "ConsultationFee", precision = 18, scale = 2)
    private BigDecimal consultationFee;

    @Column(name = "CredentialImageUrl", length = 300)
    private String credentialImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    private Department department;

    @Column(name = "Discriminator", nullable = false, length = 21)
    private String discriminator;

    @Column(name = "YearsOfExperience")
    private Integer yearsOfExperience;

    @Column(name = "Status")
    private Integer status;
}