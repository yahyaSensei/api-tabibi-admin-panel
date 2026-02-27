package com.sensei.apitabibiadminpanel.repository.Identity;

import com.sensei.apitabibiadminpanel.entities.Identity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}