package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.model.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    Optional<Permission> findByName(String permissionName);

}
