package com.eduzone.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "auth_refresh_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AuthAccount account;

    private String token;
    private Timestamp expiryDate;
}
