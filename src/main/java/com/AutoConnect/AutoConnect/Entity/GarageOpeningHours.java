package com.AutoConnect.AutoConnect.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GarageOpeningHours extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime openingHour;
    private LocalTime closingHour;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
}
