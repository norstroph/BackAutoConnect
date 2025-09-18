package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.CarDTO;
import com.AutoConnect.AutoConnect.Entity.Car;

public class CarMapper {
    public static CarDTO CarToCarDTO(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setImmat(car.getImmat());
        carDTO.setKm(car.getKm());
        carDTO.setMake(car.getMake());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        return carDTO;
    }
}
