package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.GarageOpeningHoursDto;
import com.AutoConnect.AutoConnect.Entity.GarageOpeningHours;

public class GarageOpeningHoursMapper {
    public static GarageOpeningHoursDto GarageOpeningHoursToGarageOpenHoursDto(GarageOpeningHours garageOpeningHours){
        GarageOpeningHoursDto garageOpeningHoursDto = new GarageOpeningHoursDto();
        garageOpeningHoursDto.setDayOfWeek(garageOpeningHours.getDayOfWeek());
        garageOpeningHoursDto.setOpeningHour(garageOpeningHours.getOpeningHour());
        garageOpeningHoursDto.setClosingHour(garageOpeningHours.getClosingHour());
        return garageOpeningHoursDto;
    }
}
