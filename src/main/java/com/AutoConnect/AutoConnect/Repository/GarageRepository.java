package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Garage;

import com.AutoConnect.AutoConnect.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {
    Garage findBySiren(String siren);
    @Override
    Optional<Garage> findById(Long garageId);
    ;
}
