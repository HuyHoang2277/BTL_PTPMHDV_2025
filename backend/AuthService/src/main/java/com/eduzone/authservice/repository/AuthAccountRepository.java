package com.eduzone.authservice.repository;

import com.eduzone.authservice.entity.AuthAccount;
import com.eduzone.authservice.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthAccountRepository extends JpaRepository<AuthAccount, Integer> {

    Optional<AuthAccount> findByEmail(String email);

    Optional<AuthAccount> findByProviderAndProviderId(Provider provider, String providerId);

}
