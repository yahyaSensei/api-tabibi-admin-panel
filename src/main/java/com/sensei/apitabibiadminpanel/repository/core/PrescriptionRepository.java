package com.sensei.apitabibiadminpanel.repository.core;

import com.sensei.apitabibiadminpanel.entities.core.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
}