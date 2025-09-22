package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.AppointmentRequestDTO;
import com.AutoConnect.AutoConnect.DTO.ResponseAppointmentGarageDTO;
import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.AppointmentMapper;
import com.AutoConnect.AutoConnect.Mapper.ServiceMapper;
import com.AutoConnect.AutoConnect.Mapper.UserMapper;
import com.AutoConnect.AutoConnect.Repository.AppointmentRepository;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import com.AutoConnect.AutoConnect.Repository.ServiceRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final JwtUtil jwtUtil;
    private final AppointmentRepository appointemantRepository;
    private final UserRepository userRepository;
    private final GarageRepository garageRepository;
    private final ServiceRepository serviceRepository;

    public AppointmentService(JwtUtil jwtUtil, AppointmentRepository appointemantRepository, UserRepository userRepository, GarageRepository garageRepository, ServiceRepository serviceRepository) {
        this.jwtUtil = jwtUtil;
        this.appointemantRepository = appointemantRepository;
        this.userRepository = userRepository;
        this.garageRepository = garageRepository;
        this.serviceRepository = serviceRepository;
    }

    public AppointmentRequestDTO saveAppointment(AppointmentRequestDTO appointmentRequestDTO, String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();

        String userEmail = jwtUtil.extractEmail(token);
        System.out.println(userEmail);
        System.out.println(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.toString());

        Garage garage = garageRepository.findById(appointmentRequestDTO.getGarageId()).orElseThrow(() -> new RuntimeException("Garage not found"));
        List<Long> serviceIds = appointmentRequestDTO.getServiceId();
        List<Services> services = serviceRepository.findAllById(serviceIds);
        Appointment appointment = AppointmentMapper.appointmentToAppointmentDTO(appointmentRequestDTO, user, garage, services);
        Appointment createdAppointment = appointemantRepository.save(appointment);

        return appointmentRequestDTO;
    }

    @Transactional
    public List<ResponseAppointmentGarageDTO> getAllGarageAppointement(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userEmail = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Garage GarageId = garageRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));
        List<Appointment> appointmentListGarage = appointemantRepository.findByGarageId(GarageId.getId())
                .orElseThrow(() -> new RuntimeException("appointmentListGarage not found"));
        List<ResponseAppointmentGarageDTO> responseList = new ArrayList<>();

        for (Appointment appointment : appointmentListGarage) {
            User customer = appointment.getCustomer();
            if (appointment.getTechnician() != null) {
                User technician = appointment.getTechnician();
            }
            User technician = appointment.getTechnician();
            List<Services> services = appointment.getServices();
            List<ServiceDTO> serviceDTOS = ServiceMapper.servicesToServicesDTO(services);

            ResponseAppointmentGarageDTO responseAppointmentGarageDTO = AppointmentMapper.responseAppointmentGarageDTO(appointment, customer, technician, serviceDTOS);
            responseList.add(responseAppointmentGarageDTO);

            /*for(Services service : services){
                String newServicesName =  service.getName();
                String ServiceDescription = service.getDescription();
                return
            }*/

        }
        return responseList;

    }

    public UserResponseDTO addTechnicianToAppointment(Long idAppointment, Long idTechnician) {
        Appointment appointment = appointemantRepository.findById(idAppointment).orElseThrow(() -> new RuntimeException("appointment not found"));
        User tech = userRepository.findById(idTechnician).orElseThrow(() -> new RuntimeException("UserTech not found"));

        appointemantRepository.updateTech(idAppointment, tech);
        return UserMapper.UserToUserResponseDTO(tech);
    }

    public List<AppointmentRequestDTO> getAllAppointments(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userEmail = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Appointment> appointmentList = appointemantRepository.findByCustomerId(user.getId());
        List<AppointmentRequestDTO> appointmentRequestDTOList = AppointmentMapper.appointmentToAppointmentDTO(appointmentList);
        return appointmentRequestDTOList;

    }
}


