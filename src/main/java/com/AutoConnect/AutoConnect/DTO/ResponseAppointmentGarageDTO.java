package com.AutoConnect.AutoConnect.DTO;

import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAppointmentGarageDTO {
    private Date startDate;
    private Date endDate;
    private List<ServiceDTO> serviceDTOS;
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private Long techicianId;
    private  String technicianName;
    private String technicianSurname;


}
