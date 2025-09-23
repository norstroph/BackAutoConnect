package com.AutoConnect.AutoConnect.DTO;

import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppointmentGarageDTO {
    private Long id;
    private LocalDateTime  startDate;
    private LocalDateTime endDate;
    private List<ServiceDTO> serviceDTOS;
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private Long techicianId;
    private  String technicianName;
    private String technicianSurname;

}
