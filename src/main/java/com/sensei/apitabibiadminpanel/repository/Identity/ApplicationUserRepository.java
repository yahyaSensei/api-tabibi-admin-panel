package com.sensei.apitabibiadminpanel.repository.Identity;

import com.sensei.apitabibiadminpanel.entities.Identity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
}