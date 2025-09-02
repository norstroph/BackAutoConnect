package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServiceRepository extends JpaRepository<Services,Long> {

    Optional<Services> findById(Long serviceId);
}
