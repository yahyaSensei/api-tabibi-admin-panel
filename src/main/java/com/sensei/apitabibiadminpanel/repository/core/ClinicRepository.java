package com.sensei.apitabibiadminpanel.repository.core;

import com.sensei.apitabibiadminpanel.entities.core.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String> {
}