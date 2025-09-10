package com.AutoConnect.AutoConnect.DTO;

import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarageDTO {
    private Long id;
    private String name;
    private String numeroVoie;
    private String typeVoie;
    private String libelleVoie;
    private String codePostal;
    private String libelleCommune;
    private String codeCommune;
    private Double longitude;
    private Double latitude;
    private String phone;
}
