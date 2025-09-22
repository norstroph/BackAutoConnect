package com.AutoConnect.AutoConnect.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
@Getter
@Setter
public class AppointmentDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
