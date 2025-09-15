package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.AppointmentRequestDTO;

import com.AutoConnect.AutoConnect.Service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    };

    @PostMapping
    public ResponseEntity<AppointmentRequestDTO> createAppointment(@RequestHeader("Authorization") String authHeader, @Valid  @RequestBody AppointmentRequestDTO appointmentRequestDTO){
    return new  ResponseEntity<>(appointmentService.saveAppointment(appointmentRequestDTO,authHeader), HttpStatus.CREATED);
    };


}
