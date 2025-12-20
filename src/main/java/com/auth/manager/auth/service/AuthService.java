package com.auth.manager.auth.service;

import com.auth.manager.auth.controller.dto.LoginRequest;
import com.auth.manager.auth.controller.dto.RegisterRequest;
import com.auth.manager.auth.controller.dto.TokenResponse;
import com.auth.manager.auth.repository.Token;
import com.auth.manager.auth.repository.Token.TokenType;
import com.auth.manager.auth.repository.TokenRepository;
import com.auth.manager.user.User;
import com.auth.manager.user.UserRepository;

import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public TokenResponse register(final RegisterRequest request) {
        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        return new TokenResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    public TokenResponse login(final LoginRequest request) {
        return null;
    }

    public TokenResponse refreshToken(final String authHeader) {
        return null;
    }
}

