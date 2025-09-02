package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {

    private Long garageId;
    private Long carID;
    private Date startDate;
    private Date endDate;
    private String comment;
    private List<Long> serviceId;

}
