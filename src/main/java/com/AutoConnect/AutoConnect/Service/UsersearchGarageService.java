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
        System.out.println(user.getLatitude() + " / " + user.getLongitude());
        garageRepository.findAll().forEach(g ->
                System.out.println("Garage: " + g.getName() + " lat=" + g.getLatitude() + " long=" + g.getLongitude())
        );

        List<Garage> listeGarageKm = garageRepository.findAll().stream()
                .filter(garage -> {
                    GlobalPosition garagePos = new GlobalPosition(garage.getLatitude(), garage.getLongitude(), 0.0);

                    double distanceMeters = geoCalc
                            .calculateGeodeticCurve(reference, userPos, garagePos)
                            .getEllipsoidalDistance();
                    System.out.println("Garage: " + garage.getName());
                    garage.getServices().forEach(s -> System.out.println(" - Service BDD: " + s.getName()));
                    services.forEach(req -> System.out.println(" - Service Requête: " + req.getName()));

                    return distanceMeters <= radiusKm * 1000;
                }).toList();

        System.out.println("==== Garages retenus après filtrage distance ====");
        listeGarageKm.forEach(g -> {
            System.out.println("Garage retenu: " + g.getName()
                    + " (lat=" + g.getLatitude() + ", long=" + g.getLongitude() + ")");
            g.getServices().forEach(s -> System.out.println("   -> Service: " + s.getName()));
        });
        // 🔹 Avant le filtrage, on affiche la liste des services demandés par l'utilisateur
        System.out.println("===== Services demandés par l'utilisateur =====");
        services.forEach(req -> System.out.println(" -> " + req.getId()));

        List<Garage> finalListGarage = listeGarageKm.stream()
                .filter(garage -> {
                    System.out.println("=== Vérification du garage: " + garage.getId() + " (" + garage.getName() + ") ===");

                    // 🔹 Affiche tous les services du garage
                    System.out.println("Services dans le garage :");
                    garage.getServices().forEach(s ->
                            System.out.println(" - Service ID: " + s.getId() + " | Name: " + s.getName())
                    );

                    // 🔹 Affiche les services demandés par l'utilisateur
                    System.out.println("Services demandés par l'utilisateur :");
                    services.forEach(req ->
                            System.out.println(" - Service ID: " + req.getId() + " | Name: " + req.getName())
                    );

                    // 🔹 Tous les services demandés doivent exister dans le garage (par ID)
                    boolean match = services.stream()
                            .allMatch(req ->
                                    garage.getServices().stream()
                                            .anyMatch(service -> req.getId().equals(service.getId()))
                            );

                    System.out.println("Résultat du filtre pour ce garage : " + match + "\n");
                    return match;
                })
                .toList();
        System.out.println("==== Garages retenus après filtrage (finalListGarage) ====");
        finalListGarage.forEach(g -> {
            System.out.println("Garage ID: " + g.getId()
                    + " | Name: " + g.getName()
                    + " | Latitude: " + g.getLatitude()
                    + " | Longitude: " + g.getLongitude()
                    + " | Services: " + g.getServices().stream()
                    .map(s -> s.getName() + " (ID:" + s.getId() + ")")
                    .toList()
            );
        });

        List<GarageDTO> finalRendurlisteGarage = finalListGarage.stream()
                .map(GarageMapper::garageToGarageDTo)
                .toList();


        return finalRendurlisteGarage;

    }

}
