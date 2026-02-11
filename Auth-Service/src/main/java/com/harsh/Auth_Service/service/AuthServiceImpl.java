package com.harsh.Auth_Service.service;

import com.harsh.Auth_Service.dto.AuthResponse;
import com.harsh.Auth_Service.dto.LoginRequest;
import com.harsh.Auth_Service.dto.LoginResponse;
import com.harsh.Auth_Service.dto.RegisterRequest;
import com.harsh.Auth_Service.entity.Role;
import com.harsh.Auth_Service.entity.User;
import com.harsh.Auth_Service.repository.UserRepository;
import com.harsh.Auth_Service.security.JwtService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        return registerWithRole(request, Role.USER);
    }

    @Override
    public AuthResponse registerAdmin(RegisterRequest request) {
        return registerWithRole(request, Role.ADMIN);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.isEnabled()) {
            throw new RuntimeException("User is disabled");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }

    private AuthResponse registerWithRole(RegisterRequest request, Role role) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .enabled(true)
                .build();

        userRepository.save(user);

        return new AuthResponse("Registered successfully as " + role);
    }
}
