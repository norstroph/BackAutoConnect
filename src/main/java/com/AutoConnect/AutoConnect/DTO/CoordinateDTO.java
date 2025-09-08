package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gavaghan.geodesy.GlobalCoordinates;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateDTO {
    private Double longitude ;
    private Double Latitude;

    @Override
    public String toString() {
        return "CoordinateDTO{lat=" + Latitude + ", lon=" + longitude + "}";
    }
}
