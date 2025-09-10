package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.CoordinateDTO;
import com.AutoConnect.AutoConnect.DTO.GarageDTO;
import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
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
    private final UserRepository userRepository;


    public UsersearchGarageService(RestTemplateService restTemplateService,GarageRepository garageRepository,UserRepository userRepository){
        this.garageRepository = garageRepository;
        this.restTemplateService = restTemplateService;
        this.userRepository =userRepository;

    }
    public  List<GarageDTO> getGarageForUser(String Coordinate, Double radiusKm, List<ServiceDTO> services){
        CoordinateDTO user = restTemplateService.createGeocodeUser(Coordinate);

        return  getGarageForUserWithGPS(user.getLatitude(), user.getLongitude(),radiusKm, services);
    }

    public  List<GarageDTO>  getGarageForUserWithGPS(Double latitude, Double longitude ,Double radiusKm, List<ServiceDTO> services) {

        GlobalPosition userPos = new GlobalPosition(latitude, longitude , 0.0);
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

        /*List<GarageDTO> finalRendurlisteGarage = finalListGarage.stream()
                .map(GarageMapper::garageToGarageDTo)
                .toList();*/
        List<GarageDTO> finalRendurlisteGarage = finalListGarage.stream()
                .map(g -> {
                    System.out.println("Engineer du garage " + g.getName() + " : " + g.getEngineer());

                    User user = null;
                    if (g.getEngineer() != null) {
                        user = userRepository.findById(g.getEngineer().getId())
                                .orElse(null);
                    }
                    return GarageMapper.garageToGarageDTo(g, user);
                })
                .toList();

        if (!finalRendurlisteGarage.isEmpty()) {
            System.out.println("Liste des garages :");
            finalRendurlisteGarage.forEach(garage -> System.out.println(garage));
        } else {
            System.out.println("Aucun garage trouvé.");
        }

        return finalRendurlisteGarage;

    }

}
