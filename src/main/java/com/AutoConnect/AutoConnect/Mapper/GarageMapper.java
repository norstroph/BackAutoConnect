package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.*;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.Feature;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.GeocodeResponse;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.Geometry;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;

public class GarageMapper {
    public static Garage adressDTOToGarage(SirenApiResponseDTO sirenApiResponseDTO, UserRequestDTO userRequestDTO, User user){
        UniteLegaleDTO unite = sirenApiResponseDTO.getUniteLegale();
        AdressDTO adress = unite.getEtablissementSiege();
        adress.setDenomination(unite.getDenomination());

        Garage garage = new Garage();

        garage.setEngineer(user);
        garage.setSiren(userRequestDTO.getSiren());
        garage.setName(adress.getDenomination());
        garage.setCodeCommune(adress.getCodeCommune());
        garage.setCodePostal(adress.getCodePostal());
        garage.setLibelleCommune(adress.getLibelleCommune());
        garage.setNumeroVoie(adress.getNumeroVoie());
        garage.setLibelleVoie(adress.getLibelleVoie());
        garage.setTypeVoie(adress.getTypeVoie());

        return garage;
    }
    public static GarageDTO garageToGarageDTo(Garage garage,User user){
        GarageDTO garageDTO = new GarageDTO();

        garageDTO.setName(garage.getName());
        garageDTO.setCodeCommune(garage.getCodeCommune());
        garageDTO.setId(garage.getId());
        garageDTO.setLibelleCommune(garage.getLibelleCommune());
        garageDTO.setCodePostal(garage.getCodePostal());
        garageDTO.setLibelleVoie(garage.getLibelleVoie());
        garageDTO.setLatitude(garage.getLatitude());
        garageDTO.setLongitude(garage.getLongitude());
        garageDTO.setCodePostal(garage.getCodePostal());
        garageDTO.setTypeVoie(garage.getTypeVoie());

        if (user != null) {
            garageDTO.setPhone(user.getPhone());
        } else {
            garageDTO.setPhone(null);
        }

        return garageDTO;
    };

    public static Garage geocodeResponseTogarage(GeocodeResponse geocodeResponse,Garage garage) {
        Feature newFeature = geocodeResponse.getFeatures().getFirst();
        Geometry geometry = newFeature.getGeometry();
        Double longitude = geometry.getCoordinates().getFirst();
        Double latitude = geometry.getCoordinates().get(1);
        garage.setLongitude(longitude);
        garage.setLatitude(latitude);
        return garage;

    }
}
