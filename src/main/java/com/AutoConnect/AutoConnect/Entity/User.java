package com.AutoConnect.AutoConnect.Entity;


import com.AutoConnect.AutoConnect.Entity.Enum.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;
    private String name;
    private String email;
    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;


}
