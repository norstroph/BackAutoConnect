package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.AppointmentDTO;
import com.AutoConnect.AutoConnect.DTO.AvailabilityGarageDTO;
import com.AutoConnect.AutoConnect.DTO.GarageOpeningHoursDto;
import com.AutoConnect.AutoConnect.Entity.GarageOpeningHours;

import java.util.List;


public class AvailabilityGarageDtOMapper {
    public static AvailabilityGarageDTO entityToAvailabilityGarageDtO(  List<AppointmentDTO> appointmentDTO, List<GarageOpeningHoursDto> garageOpeningHours){
        AvailabilityGarageDTO availabilityGarageDTO = new AvailabilityGarageDTO();
        availabilityGarageDTO.setGarageOpeningHours(garageOpeningHours);
        availabilityGarageDTO.setAppointment(appointmentDTO);
        return availabilityGarageDTO;
    }
}
