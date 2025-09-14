package com.kiranakottu.auth_service.repository;

import com.kiranakottu.auth_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role findById(long id);
}
