package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.AdressDTO;
import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;

public class GarageMapper {
    public static Garage adressDTOToGarage(AdressDTO adress, UserRequestDTO userRequestDTO, User user){
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
}
