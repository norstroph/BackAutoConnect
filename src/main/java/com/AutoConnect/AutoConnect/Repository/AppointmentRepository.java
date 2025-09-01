package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<List<Appointment>> findByGarageId(Long garageId);
}
