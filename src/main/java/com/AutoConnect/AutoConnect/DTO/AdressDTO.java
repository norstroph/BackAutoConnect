package com.AutoConnect.AutoConnect.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdressDTO {
    private String denomination;
    @JsonProperty("numero_voie")
    private String numeroVoie;
    @JsonProperty("type_voie")
    private String typeVoie;
    @JsonProperty("libelle_voie")
    private String libelleVoie;
    @JsonProperty("code_postal")
    private String codePostal;
    @JsonProperty("libelle_commune")
    private String libelleCommune;
    @JsonProperty("code_commune")
    private String codeCommune;
}


