package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.AppointmentRequestDTO;
import com.AutoConnect.AutoConnect.DTO.ResponseAppointmentGarageDTO;
import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    public static Appointment appointmentToAppointmentDTO(
            AppointmentRequestDTO appointment,
            User user,
            Garage garage,
            List<Services> services
    ) {
        Appointment appointment1 = new Appointment();

        appointment1.setStartDate(appointment.getStartDate());
        appointment1.setEndDate(appointment.getEndDate());
        appointment1.setGarage(garage);
        appointment1.setCustomer(user);
        appointment1.setServices(services);

        return appointment1;
    }

    public static ResponseAppointmentGarageDTO responseAppointmentGarageDTO(Appointment appointment, User customer, User technician, List<ServiceDTO> services){
        ResponseAppointmentGarageDTO responseAppointmentGarageDTO = new ResponseAppointmentGarageDTO();

        responseAppointmentGarageDTO.setStartDate(appointment.getStartDate());
        responseAppointmentGarageDTO.setEndDate(appointment.getEndDate());
        responseAppointmentGarageDTO.setServiceDTOS(services);

        responseAppointmentGarageDTO.setCustomerId( customer.getId());
        responseAppointmentGarageDTO.setCustomerName(customer.getName());
        responseAppointmentGarageDTO.setCustomerSurname(customer.getUsername());
        responseAppointmentGarageDTO.setCustomerPhone(customer.getPhone());
        if( technician != null){
            responseAppointmentGarageDTO.setTechicianId(technician.getId());
            responseAppointmentGarageDTO.setTechicianId(technician.getId());
            responseAppointmentGarageDTO.setTechnicianName(technician.getName());
            responseAppointmentGarageDTO.setTechnicianSurname(technician.getUsername());
        }
        return  responseAppointmentGarageDTO;

    }

    public static List<AppointmentRequestDTO> appointmentToAppointmentDTO(List<Appointment> appointment){
        List<AppointmentRequestDTO> appointmentRequestDTOList = new ArrayList<>();
        for(Appointment appointment1 : appointment){
            AppointmentRequestDTO appointmentRequestDTO = new AppointmentRequestDTO();

            appointmentRequestDTO.setStartDate(appointment1.getStartDate());
            appointmentRequestDTO.setEndDate(appointment1.getEndDate());

            appointmentRequestDTOList.add(appointmentRequestDTO);
        }
        return appointmentRequestDTOList;
    }
}
