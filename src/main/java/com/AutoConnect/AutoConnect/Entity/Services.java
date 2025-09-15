package com.AutoConnect.AutoConnect.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services extends BaseEntity{
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    private List<Garage> garages;

    @ManyToMany(mappedBy = "services")
    private List<Appointment> appointments;
}
