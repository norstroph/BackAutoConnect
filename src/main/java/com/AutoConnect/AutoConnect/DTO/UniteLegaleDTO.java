package com.AutoConnect.AutoConnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniteLegaleDTO {
    private String denomination;

    @JsonProperty("etablissement_siege")
    private AdressDTO etablissementSiege;
    // getters/setters
}
