package com.AutoConnect.AutoConnect.Repository;

import com.AutoConnect.AutoConnect.Entity.Appointment;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<List<Appointment>> findByGarageId(Long garageId);

    @Transactional
    @Modifying
    @Query("UPDATE Appointment a SET a.technician = :tech WHERE a.id = :id")
    void updateTech(@Param("id") Long appointmentId, @Param("tech") User tech);

    @Query("SELECT a FROM Appointment a WHERE a.customer.id = :customerId")
    List<Appointment> findByCustomerId(@Param("customerId") Long customerId);
}
