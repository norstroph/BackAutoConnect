package com.AutoConnect.AutoConnect.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage extends BaseEntity {

    private String name;
    private String siren;
    private String numeroVoie;
    private String typeVoie;
    private String libelleVoie;
    private String codePostal;
    private String libelleCommune;
    private String codeCommune;
    private Integer quantityTechnicians;
    private Double longitude;
    private Double latitude;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User engineer;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
    private List<User> technicians;

    @ManyToMany
    private List<Services> Services;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GarageOpeningHours> openingHours = new ArrayList<>();

}
