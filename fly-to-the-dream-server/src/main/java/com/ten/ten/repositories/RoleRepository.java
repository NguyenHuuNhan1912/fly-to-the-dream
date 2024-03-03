package com.ten.ten.repositories;

import com.ten.ten.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByNameRole(String name);

}
