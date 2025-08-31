package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Garage,Long> {
    Appointment save(Appointment appointment);
}
