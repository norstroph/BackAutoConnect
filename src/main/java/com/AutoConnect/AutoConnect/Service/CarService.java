package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.CarDTO;
import com.AutoConnect.AutoConnect.Entity.Car;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.CarMapper;
import com.AutoConnect.AutoConnect.Repository.CarRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
   private final JwtUtil jwtUtil;
   private final UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public CarDTO saveCarUser(Car car, String authHeader){
        String token = authHeader.replace("Bearer ", "").trim();

        String userEmail = jwtUtil.extractEmail(token);
        System.out.println(userEmail);
        System.out.println(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        car.setUserId(user.getId());
        Car newCar = carRepository.save(car);
        CarDTO carDTO = CarMapper.CarToCarDTO(newCar);
        
        return carDTO;
    }

    public List<CarDTO> getAllCars(String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();

        String userEmail = jwtUtil.extractEmail(token);
        System.out.println(userEmail);
        System.out.println(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Car> cars = carRepository.findByUserId(user.getId());
        List<CarDTO> carDTOs = cars.stream().map(CarMapper::CarToCarDTO).toList();
        return carDTOs;
    }
}
