package com.eduzone.authservice.repository;

import com.eduzone.authservice.entity.AuthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRefreshTokenRepository extends JpaRepository<AuthRefreshToken, Integer> {

    Optional<AuthRefreshToken> findByToken(String token);

}
