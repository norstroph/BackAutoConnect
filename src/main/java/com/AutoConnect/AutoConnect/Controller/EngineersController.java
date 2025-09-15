package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.ResponseAppointmentGarageDTO;
import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Enum.Role;
import com.AutoConnect.AutoConnect.Service.AppointmentService;
import com.AutoConnect.AutoConnect.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engineers")
public class EngineersController {
    private final UserService userService;
    private final AppointmentService appointmentService;
    public EngineersController(UserService userService , AppointmentService appointmentService){
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/createTech")
    public ResponseEntity<UserResponseDTO> createUser(@RequestHeader("Authorization") String authHeader ,@Valid @RequestBody UserRequestDTO userRequest) {
        return new ResponseEntity<>(userService.saveTechnicians(userRequest,authHeader ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllTechnicians(){
        return new ResponseEntity<>(userService.findByRoleTechnicians(Role.TECHNICIAN),HttpStatus.FOUND);
    }
    @GetMapping("/all-Apointment-Garage")
    public ResponseEntity<List<ResponseAppointmentGarageDTO>> AllAppointmentGarage(@RequestHeader("Authorization") String authHeader){
        return new ResponseEntity<>(appointmentService.getAllGarageAppointement(authHeader),HttpStatus.FOUND);
    }
    @PutMapping("/{id-appointment}/{id-tech}")
    public ResponseEntity<UserResponseDTO> addTechnicianToAppointment(@PathVariable Long idAppointment , @PathVariable Long idTech){
        return new  ResponseEntity<>(appointmentService.addTechnicianToAppointment(idAppointment,idTech), HttpStatus.CREATED);
    }
}
