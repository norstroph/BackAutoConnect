package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.*;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.Feature;
import com.AutoConnect.AutoConnect.DTO.ExternalAPI.GeocodeResponse;

import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.CoordinateMapper;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;


import java.util.List;


@Service
public class RestTemplateService {

    @Value("${siren.api.key}")
    private String apiKey;

    private final GarageRepository garageRepository;

    public RestTemplateService(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T callApi(String url, Class<T> responseType, HttpEntity<?> entity, Object... uriVariables) {
        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType,
                uriVariables
        );
        return response.getBody();
    }


    public Garage createGarage(UserRequestDTO userRequest, User created) {
        String siren = userRequest.getSiren().replaceAll("\\s", "");
        String apiUrl = String.format("https://data.siren-api.fr/v3/unites_legales/%s", siren);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Client-Secret", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        SirenApiResponseDTO sirenApiResponseDTO = callApi(apiUrl, SirenApiResponseDTO.class, entity);

        return  GarageMapper.adressDTOToGarage(sirenApiResponseDTO, userRequest, created);
    }

    public Garage createGeocode(Garage garage) {
        String BASE_URL = "https://data.geopf.fr/geocodage/search?q={number}+{Voie}+{NomVoie}+{codePostal}+{name}&limit=1";
        GeocodeResponse geocodeResponse = callApi(BASE_URL, GeocodeResponse.class,
                null, garage.getNumeroVoie(), garage.getTypeVoie(), garage.getLibelleVoie(), garage.getCodePostal(), garage.getName()
        );
        Garage newgarage = GarageMapper.geocodeResponseTogarage(geocodeResponse, garage);
        return garageRepository.save(newgarage);
    }

    public List<DataDTO> getMarkCarApi() {
        String url = "https://carapi.app/api/makes/v2";
        CarpiDTO carpiDTO = callApi(url, CarpiDTO.class, null);
        return carpiDTO.getData();
    }

    public List<DataDTO> getModelCarApi(String mark) {
        String url = "https://carapi.app/api/models/v2/?make={mark}";
        CarpiDTO carpiDTO = callApi(url ,CarpiDTO.class,null,mark);
        return   carpiDTO.getData();
    }

    public YearsDTO[] getYearCarApi(String mark, String model) {
        String url = "https://carapi.app/api/years/v2?make={mark}&?models={model}";
        return callApi( url, YearsDTO[].class, null,  mark, model);
    }

    public CoordinateDTO createGeocodeUser(String addressUser) {
        String BASE_URL = "https://data.geopf.fr/geocodage/search?q={addressUser}&limit=1";
        GeocodeResponse geocodeResponse = callApi(BASE_URL, GeocodeResponse.class,null, addressUser);
        CoordinateDTO coordinateDTO = CoordinateMapper.geocodeResponseToCoordinateDTO(geocodeResponse);

        return coordinateDTO;
    }
}