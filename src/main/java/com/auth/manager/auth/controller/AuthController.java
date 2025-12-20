package com.auth.manager.auth.controller;

import com.auth.manager.auth.controller.dto.LoginRequest;
import com.auth.manager.auth.controller.dto.RegisterRequest;
import com.auth.manager.auth.controller.dto.TokenResponse;
import com.auth.manager.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody final RegisterRequest request) {
        final TokenResponse token = authService.register(request);
        return ResponseEntity.ok(token);
    }

    /**
     * AUTENTICACIÓN
     * Devuelve access token + refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody final LoginRequest LoginRequest) {
        final TokenResponse token = authService.login(LoginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {

        return authService.refreshToken(authHeader);
    }
}
