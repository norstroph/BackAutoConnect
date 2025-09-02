package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.Entity.Services;
import com.AutoConnect.AutoConnect.Repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {
    private final ServiceRepository serviceRepository;
    public RepairService(ServiceRepository serviceRepository){
        this.serviceRepository = serviceRepository;
    }
    public List<Services> getAllService(){
        return serviceRepository.findAll();
    }
}
