package com.sensei.apitabibiadminpanel.repository.core;

import com.sensei.apitabibiadminpanel.entities.core.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, UUID> {
}