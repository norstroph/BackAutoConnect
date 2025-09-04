package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.*;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;


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
                apiUrl,
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


        return garage;
    }

    public Garage createGeocode(Garage garage){
          RestTemplate restTemplate = new RestTemplate();

          String BASE_URL = "https://data.geopf.fr/geocodage/search?q={number}+{Voie}+{NomVoie}+{codePostal}+{name}&limit=1";

        ResponseEntity<GeocodeResponse> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                GeocodeResponse.class,
                garage.getNumeroVoie(),
                garage.getTypeVoie(),
                garage.getLibelleVoie(),
                garage.getCodePostal(),
                garage.getName()
        );
        GeocodeResponse geocodeResponse = response.getBody();
        Feature newFeature =  geocodeResponse.getFeatures().getFirst();
        Geometry geometry = newFeature.getGeometry();
        Double longitude = geometry.getCoordinates().getFirst();
        Double latitude = geometry.getCoordinates().get(1);
        garage.setLongitude(longitude);
         garage.setLatitude(latitude);
         return garageRepository.save(garage);



    }

    public List<DataDTO> getMarkCarApi() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://carapi.app/api/makes/v2";

        ResponseEntity<CarpiDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                CarpiDTO.class

        );
        CarpiDTO dataDTO  = response.getBody();
        List<DataDTO> dataResponse = dataDTO.getData();


        return dataResponse ;

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<DataDTO> getModelCarApi(String mark) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://carapi.app/api/models/v2/?make={mark}";

        ResponseEntity<CarpiDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                CarpiDTO.class,
                mark

        );
        CarpiDTO dataDTO  = response.getBody();
        List<DataDTO> dataResponse = dataDTO.getData();


        return dataResponse ;
    }
/////////////////////////////////////////////

    public YearsDTO[] getYearCarApi(String mark, String model) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://carapi.app/api/years/v2?make={mark}&?models={model}";

       List<YearsDTO> carpiDTO = new ArrayList<>();

       ResponseEntity<YearsDTO[]> response = restTemplate.getForEntity(
                url,
                YearsDTO[].class,
                mark,
                model

        );
        YearsDTO[] dataDTO = response.getBody();


        return dataDTO;
    }
/////////////////////////////////////////////////////
    public  CoordinateDTO createGeocodeUser(String addressUser){
        RestTemplate restTemplate = new RestTemplate();

        String BASE_URL = "https://data.geopf.fr/geocodage/search?q={addressUser}&limit=1";

        ResponseEntity<GeocodeResponse> response = restTemplate.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                GeocodeResponse.class,
                addressUser

        );
      
        GeocodeResponse geocodeResponse = response.getBody();
        Feature newFeature =  geocodeResponse.getFeatures().getFirst();
        Geometry geometry = newFeature.getGeometry();
        Double longitude = geometry.getCoordinates().getFirst();
        Double latitude = geometry.getCoordinates().get(1);
        CoordinateDTO coordinateDTO = new CoordinateDTO();
        coordinateDTO.setLongitude(longitude);
        coordinateDTO.setLatitude(latitude);

        return coordinateDTO;


    }

}
