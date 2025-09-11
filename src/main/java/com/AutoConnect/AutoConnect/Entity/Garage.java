package com.AutoConnect.AutoConnect.Entity;

import com.AutoConnect.AutoConnect.DTO.Feature;
import com.AutoConnect.AutoConnect.DTO.GeocodeResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String siren;
    private String numeroVoie;
    private String typeVoie;
    private String libelleVoie;
    private String codePostal;
    private String libelleCommune;
    private String codeCommune;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User engineer;
    private Integer quantityTechnicians;
    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<User> technicians;
    private Double longitude;
    private Double latitude;
    @ManyToMany
    private List<Services> Services;
    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GarageOpeningHours> openingHours = new ArrayList<>();

}
