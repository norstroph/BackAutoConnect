package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.*;
import com.AutoConnect.AutoConnect.Entity.*;
import com.AutoConnect.AutoConnect.Mapper.AvailabilityGarageDtOMapper;
import com.AutoConnect.AutoConnect.Mapper.GarageMapper;
import com.AutoConnect.AutoConnect.Mapper.GarageOpeningHoursMapper;
import com.AutoConnect.AutoConnect.Repository.GarageOpeningHoursRepository;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UsersearchGarageService {
    private final GarageRepository garageRepository;
    private final Ellipsoid reference = Ellipsoid.WGS84;
    private final RestTemplateService restTemplateService;
    private final UserRepository userRepository;
    private final GarageOpeningHoursRepository garageOpeningHoursRepository;


    public UsersearchGarageService(RestTemplateService restTemplateService,GarageRepository garageRepository,UserRepository userRepository,GarageOpeningHoursRepository garageOpeningHoursRepository){
        this.garageRepository = garageRepository;
        this.restTemplateService = restTemplateService;
        this.userRepository =userRepository;
        this.garageOpeningHoursRepository = garageOpeningHoursRepository;

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

    public AvailabilityGarageDTO getAllDateuseForAppointment(Long garageId) {
        List<GarageOpeningHours> garageOpeningHours = garageOpeningHoursRepository.findByGarageId(garageId);
        List<GarageOpeningHoursDto> garageOpeningHoursDtos = garageOpeningHours.stream().map(GarageOpeningHoursMapper :: GarageOpeningHoursToGarageOpenHoursDto).toList();
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new RuntimeException("garage not found"));
        int count = garage.getTechnicians().size();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        Map<Date, List<Appointment>> groupedAppointments = garage.getAppointments()
                .stream()
                .collect(Collectors.groupingBy(Appointment::getStartDate));
        for (Map.Entry<Date, List<Appointment>> entry : groupedAppointments.entrySet()) {
            if (entry.getValue().size() == count) {
                AppointmentDTO appointmentDTO = new AppointmentDTO();
                Date startDate = entry.getKey();
                appointmentDTO.setStartDate(startDate);
                Date endDate = entry.getValue().get(0).getEndDate();
                appointmentDTO.setEndDate(endDate);
                appointmentDTOS.add(appointmentDTO);
                System.out.println("Date trouvée : " + startDate + " → " + endDate);
            }
        }
        return AvailabilityGarageDtOMapper.entityToAvailabilityGarageDtO(appointmentDTOS,garageOpeningHoursDtos);
    }


}
