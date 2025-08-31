package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.AppointmentRequestDTO;
import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.AppointmentMapper;
import com.AutoConnect.AutoConnect.Repository.AppointmentRepository;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import com.AutoConnect.AutoConnect.Repository.ServiceRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Appointment saveAppointment(AppointmentRequestDTO appointmentRequestDTO, String authHeader) {
        String token = authHeader.replace("Bearer ", "");
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

        return createdAppointment;
    }


}
