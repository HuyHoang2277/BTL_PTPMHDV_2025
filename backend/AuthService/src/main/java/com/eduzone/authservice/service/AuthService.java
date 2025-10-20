package com.eduzone.authservice.service;

import com.eduzone.authservice.dto.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    AuthResponse refresh(RefreshTokenRequest request);
    AuthResponse loginWithGoogle(GoogleLoginRequest request);
}
