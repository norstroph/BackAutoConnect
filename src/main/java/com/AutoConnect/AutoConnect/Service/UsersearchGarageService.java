package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.CoordinateDTO;
import com.AutoConnect.AutoConnect.DTO.GarageDTO;
import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UsersearchGarageService {
    private final GarageRepository garageRepository;
    private final Ellipsoid reference = Ellipsoid.WGS84;
    private final RestTemplateService restTemplateService;


    public UsersearchGarageService(RestTemplateService restTemplateService,GarageRepository garageRepository){
        this.garageRepository = garageRepository;
        this.restTemplateService = restTemplateService;

    }


    public  List<GarageDTO>  getGarageForUser(String Coordinate, Double radiusKm, List<ServiceDTO> services) {
        CoordinateDTO user = restTemplateService.createGeocodeUser(Coordinate);
        GlobalPosition userPos = new GlobalPosition(user.getLatitude(), user.getLongitude(), 0.0);
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        List<Garage> listeGarageKm = garageRepository.findAll().stream()
                .filter(garage -> {
                    GlobalPosition garagePos = new GlobalPosition(garage.getLatitude(), garage.getLongitude(), 0.0);

                    double distanceMeters = geoCalc
                            .calculateGeodeticCurve(reference, userPos, garagePos)
                            .getEllipsoidalDistance();


                    return distanceMeters <= radiusKm * 1000;
                }).toList();


        List<Garage> finalListGarage = listeGarageKm.stream()
                .filter(garage -> {



                    boolean match = services.stream()
                            .allMatch(req ->
                                    garage.getServices().stream()
                                            .anyMatch(service -> req.getId().equals(service.getId()))
                            );


                    return match;
                })
                .toList();

        List<GarageDTO> finalRendurlisteGarage = finalListGarage.stream()
                .map(GarageMapper::garageToGarageDTo)
                .toList();


        return finalRendurlisteGarage;

    }

}
