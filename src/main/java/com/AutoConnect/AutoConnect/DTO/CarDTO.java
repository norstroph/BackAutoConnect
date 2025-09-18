package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String immat;
    private String km;
    private String make;
    private String model;
    private int year;
}
