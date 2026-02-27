package com.sensei.apitabibiadminpanel.repository.core;


import com.sensei.apitabibiadminpanel.entities.core.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem, UUID> {
}