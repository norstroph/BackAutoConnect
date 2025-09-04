package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.GarageDTO;
import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Service.UsersearchGarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garage")
public class GarageController {
    private final UsersearchGarageService usersearchGarageService;
    public GarageController( UsersearchGarageService usersearchGarageService){
        this.usersearchGarageService =usersearchGarageService;

    }
    @PostMapping
    public ResponseEntity<List<GarageDTO>> getAllGArage(@RequestParam String Coordinate,@RequestParam Double radiusKm,@RequestBody List<ServiceDTO> services){
     return new ResponseEntity<>(usersearchGarageService.getGarageForUser(Coordinate, radiusKm, services), HttpStatus.CREATED);
    }
}
