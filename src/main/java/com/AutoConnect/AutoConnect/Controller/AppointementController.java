package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.AppointmentRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointements")
public class AppointementController {


    private final AppointmentService appointmentService;

    public AppointementController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    };

    @PostMapping
    public ResponseEntity<AppointmentRequestDTO> createAppointemant(@RequestHeader("Authorization") String authHeader, @Valid  @RequestBody AppointmentRequestDTO appointemantRequestDTO){
    return new  ResponseEntity<>(appointmentService.saveAppointment(appointemantRequestDTO,authHeader), HttpStatus.CREATED);
    };

    @PutMapping
    public ResponseEntity<UserResponseDTO> addTechnicianToAppointment(@RequestHeader("Authorization") String authHeader){
        return new  ResponseEntity<>(appointmentService.addTechnicianToAppointment(authHeader), HttpStatus.CREATED);
    }

}
