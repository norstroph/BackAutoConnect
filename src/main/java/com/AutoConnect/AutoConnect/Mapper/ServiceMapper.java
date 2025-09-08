package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.ServiceDTO;
import com.AutoConnect.AutoConnect.Entity.Services;
import org.springframework.stereotype.Service;

import java.util.List;

public class ServiceMapper {
    public static List<ServiceDTO> servicesToServicesDTO(List<Services> services){
        List<ServiceDTO> serviceDTOs = services.stream()
                .map(s -> {
                    ServiceDTO sdto = new ServiceDTO();
                    sdto.setId(s.getId());
                    return sdto;
                })
                .toList();
        return serviceDTOs;
    };
}
