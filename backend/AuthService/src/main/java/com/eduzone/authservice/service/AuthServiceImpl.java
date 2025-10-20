package com.eduzone.authservice.service;

import com.eduzone.authservice.dto.*;
import com.eduzone.authservice.entity.*;
import com.eduzone.authservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthAccountRepository accountRepo;
    private final AuthRoleRepository roleRepo;
    private final AuthRefreshTokenRepository refreshRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GoogleAuthService googleAuthService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (accountRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        AuthAccount account = new AuthAccount();
        account.setEmail(request.getEmail());
        account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        account.setProvider(Provider.credentials);
        account.setStatus(1);

        AuthRole userRole = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        account.setRoles(Collections.singleton(userRole));

        AuthAccount saved = accountRepo.save(account);

        String accessToken = jwtService.generateToken(saved.getEmail(), Map.of("role", "ROLE_USER"));
        String refreshToken = UUID.randomUUID().toString();

        AuthRefreshToken tokenEntity = new AuthRefreshToken();
        tokenEntity.setAccount(saved);
        tokenEntity.setToken(refreshToken);
        tokenEntity.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(60 * 60 * 24 * 7))); // 7 days
        refreshRepo.save(tokenEntity);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(saved.getId())
                .email(saved.getEmail())
                .role("ROLE_USER")
                .build();
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        AuthAccount account = accountRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String role = account.getRoles().stream()
                .findFirst().map(AuthRole::getName).orElse("ROLE_USER");
        String accessToken = jwtService.generateToken(account.getEmail(), Map.of("role", role));
        String refreshToken = UUID.randomUUID().toString();

        AuthRefreshToken tokenEntity = new AuthRefreshToken();
        tokenEntity.setAccount(account);
        tokenEntity.setToken(refreshToken);
        tokenEntity.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(60 * 60 * 24 * 7)));
        refreshRepo.save(tokenEntity);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(account.getId())
                .email(account.getEmail())
                .role(role)
                .build();
    }

    @Override
    public AuthResponse refresh(RefreshTokenRequest request) {
        AuthRefreshToken refresh = refreshRepo.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refresh.getExpiryDate().before(Timestamp.from(Instant.now()))) {
            throw new RuntimeException("Refresh token expired");
        }

        AuthAccount account = refresh.getAccount();
        String role = account.getRoles().stream()
                .findFirst().map(AuthRole::getName).orElse("ROLE_USER");

        String accessToken = jwtService.generateToken(account.getEmail(), Map.of("role", role));

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refresh.getToken())
                .userId(account.getId())
                .email(account.getEmail())
                .role(role)
                .build();
    }

    @Override
    public AuthResponse loginWithGoogle(GoogleLoginRequest request) {
        String email = googleAuthService.verifyGoogleIdToken(request.getIdToken());
        if (email == null) throw new RuntimeException("Invalid Google token");

        AuthAccount account = accountRepo.findByEmail(email)
                .orElseGet(() -> {
                    AuthAccount newAcc = new AuthAccount();
                    newAcc.setEmail(email);
                    newAcc.setProvider(Provider.google);
                    newAcc.setStatus(1);
                    AuthRole userRole = roleRepo.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Default role not found"));
                    newAcc.setRoles(Collections.singleton(userRole));
                    return accountRepo.save(newAcc);
                });

        String role = account.getRoles().stream()
                .findFirst().map(AuthRole::getName).orElse("ROLE_USER");
        String accessToken = jwtService.generateToken(email, Map.of("role", role));
        String refreshToken = UUID.randomUUID().toString();

        AuthRefreshToken tokenEntity = new AuthRefreshToken();
        tokenEntity.setAccount(account);
        tokenEntity.setToken(refreshToken);
        tokenEntity.setExpiryDate(Timestamp.from(Instant.now().plusSeconds(60 * 60 * 24 * 7)));
        refreshRepo.save(tokenEntity);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(account.getId())
                .email(account.getEmail())
                .role(role)
                .build();
    }
}
