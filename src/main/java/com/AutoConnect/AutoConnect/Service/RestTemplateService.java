package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.AdressDTO;
import com.AutoConnect.AutoConnect.DTO.SirenApiResponseDTO;
import com.AutoConnect.AutoConnect.DTO.UniteLegaleDTO;
import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;


@Service
public class RestTemplateService {

    private final GarageRepository garageRepository;
    public RestTemplateService(GarageRepository garageRepository){
        this.garageRepository = garageRepository;
    }


    private final RestTemplate restTemplate = new RestTemplate();

    public Garage createGarage(UserRequestDTO userRequest, User created) {
        String siren = userRequest.getSiren().replaceAll("\\s", "");
        String apiUrl = String.format("https://data.siren-api.fr/v3/unites_legales/%s", siren);
        String apiKey = "hC97pQlkGXrwCflW7sZW4VMowHDQY6Mo";

        // Création des headers avec la clé API
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Client-Secret", apiKey);



        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<SirenApiResponseDTO> response = restTemplate.exchange(
                apiUrl ,
                HttpMethod.GET,
                entity,
                SirenApiResponseDTO.class
        );
        SirenApiResponseDTO apiResponse = response.getBody();
        UniteLegaleDTO unite = apiResponse.getUniteLegale();

        AdressDTO adress = unite.getEtablissementSiege();

        adress.setDenomination(unite.getDenomination());


        if (adress == null) {
            throw new RuntimeException("Adresse introuvable pour le SIREN : " + siren);
        }

        Garage garage = GarageMapper.adressDTOToGarage(adress, userRequest, created);






        return garageRepository.save(garage);
    }

}
