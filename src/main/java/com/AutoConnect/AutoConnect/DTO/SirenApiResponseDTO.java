package com.AutoConnect.AutoConnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SirenApiResponseDTO {
    @JsonProperty("unite_legale")
    private UniteLegaleDTO uniteLegale;
    // getters/setters
}
