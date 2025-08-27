package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Role;
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
    public EngineersController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/createTech")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest) {
        return new ResponseEntity<>(userService.saveTechnicians(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllTechnicians(){
        return new ResponseEntity<>(userService.findByRoleTechnicians(Role.TECHNICIAN),HttpStatus.FOUND);
    }
}
