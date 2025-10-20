package com.eduzone.authservice.service;

import com.eduzone.authservice.dto.*;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse registerAdmin(RegisterAdminRequest request); // chỉ cần 1 tham số
    AuthResponse login(AuthRequest request);
    AuthResponse refresh(RefreshTokenRequest request);
    AuthResponse loginWithGoogle(GoogleLoginRequest request);
}

