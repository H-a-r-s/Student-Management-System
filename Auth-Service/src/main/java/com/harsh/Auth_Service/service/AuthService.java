package com.harsh.Auth_Service.service;

import com.harsh.Auth_Service.dto.AuthResponse;
import com.harsh.Auth_Service.dto.LoginRequest;
import com.harsh.Auth_Service.dto.LoginResponse;
import com.harsh.Auth_Service.dto.RegisterRequest;

public interface AuthService {
    AuthResponse registerUser(RegisterRequest request);
    AuthResponse registerAdmin(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
