package com.eduzone.authservice.repository;

import com.eduzone.authservice.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Integer> {

    Optional<AuthRole> findByName(String name);

}
