package com.AutoConnect.AutoConnect.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeResponseDTO {
    private String label;
    private String longitude;
    private String latitude;
}
