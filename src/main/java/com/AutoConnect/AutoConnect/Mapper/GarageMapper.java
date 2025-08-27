package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.AdressDTO;
import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;

public class GarageMapper {
    public static Garage adressDTOToGarage(AdressDTO adress, UserRequestDTO userRequestDTO){
        Garage garage = new Garage();
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
}
