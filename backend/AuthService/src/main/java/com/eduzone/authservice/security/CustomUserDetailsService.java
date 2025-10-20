package com.eduzone.authservice.security;

import com.eduzone.authservice.entity.AuthAccount;
import com.eduzone.authservice.repository.AuthAccountRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthAccountRepository accountRepository;

    public CustomUserDetailsService(AuthAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthAccount account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(account.getEmail())
                .password(account.getPasswordHash())
                .roles(account.getRoles().stream().map(r -> r.getName().replace("ROLE_", "")).toArray(String[]::new))
                .build();
    }
}
