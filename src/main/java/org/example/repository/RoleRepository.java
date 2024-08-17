package org.example.repository;

import org.example.entity.Role;
import org.example.models.EnumRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByRole(EnumRoles role);
}
