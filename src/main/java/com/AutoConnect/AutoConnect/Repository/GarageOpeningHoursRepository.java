package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.GarageOpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageOpeningHoursRepository extends JpaRepository<GarageOpeningHours,Long> {
    List<GarageOpeningHours> findByGarageId(Long gargeId);
}
