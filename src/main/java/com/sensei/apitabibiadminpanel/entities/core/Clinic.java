package com.sensei.apitabibiadminpanel.entities.core;



import  com.sensei.apitabibiadminpanel.entities.Identity.ApplicationUser;
import com.sensei.apitabibiadminpanel.entities.shared.City;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "Clinics", schema = "core")
@Getter @Setter @NoArgsConstructor
public class Clinic {

    // Id is technically a Foreign Key to AspNetUsers in the SQL (1-to-1 relationship usually representing a Doctor's clinic)
    @Id
    @Column(name = "Id", length = 450)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "Id")
    private ApplicationUser doctor;

    @Column(name = "Name", nullable = false, length = 200)
    private String name;

    @Column(name = "Address", nullable = false, length = 300)
    private String address;

    @Column(name = "ImageUrl", length = 300)
    private String imageUrl;

    @Column(name = "Latitude", nullable = false)
    private Double latitude;

    @Column(name = "Longitude", nullable = false)
    private Double longitude;

    @Column(name = "PhoneNumber", nullable = false, length = 100)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityId", nullable = false)
    private City city;

    @Column(name = "Description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<WorkSchedule> workSchedules;
}