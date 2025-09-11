package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityGarageDTO {
    private List<GarageOpeningHoursDto> garageOpeningHours;
    private List<AppointmentDTO> appointment;
}
