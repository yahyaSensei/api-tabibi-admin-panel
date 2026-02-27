package com.sensei.apitabibiadminpanel.repository.core;

import com.sensei.apitabibiadminpanel.entities.core.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
}