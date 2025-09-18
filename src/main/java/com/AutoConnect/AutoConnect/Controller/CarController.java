package com.AutoConnect.AutoConnect.Controller;


import com.AutoConnect.AutoConnect.DTO.CarDTO;
import com.AutoConnect.AutoConnect.Entity.Car;
import com.AutoConnect.AutoConnect.Service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody Car car,@RequestHeader("Authorization") String authHeader){

        return new ResponseEntity<>(carService.saveCarUser(car,authHeader), HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars(@RequestHeader("Authorization") String authHeader){

        return new ResponseEntity<>(carService.getAllCars(authHeader), HttpStatus.OK);
    }
}
