package com.AutoConnect.AutoConnect.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Getter
@Setter
public class GarageOpeningHoursDto {
    private DayOfWeek dayOfWeek;

    private LocalTime openingHour;
    private LocalTime closingHour;
}
