package com.sensei.apitabibiadminpanel.repository.Identity;

import com.sensei.apitabibiadminpanel.entities.Identity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}