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
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    @Valid
    private User customer;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    @JsonManagedReference
    private User technician;
    private Date startDate;
    private Date endDate;
    private Double totalePrice;
    private String comment;
    @ManyToMany
    @JsonManagedReference
    private List<Services> service;




}
