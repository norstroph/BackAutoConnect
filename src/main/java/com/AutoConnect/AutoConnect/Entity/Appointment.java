package com.AutoConnect.AutoConnect.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity{


    private Date startDate;
    private Date endDate;
    private Double totalePrice;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Valid
    private User customer;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    @JsonManagedReference
    private User technician;

    @ManyToMany
    @JoinTable(
            name = "appointment_services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    private List<Services> services;





}
