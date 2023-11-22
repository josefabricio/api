package com.digitalholics.iotheraphy.security.infrastructure.authorization.services;

import com.digitalholics.iotheraphy.security.domain.model.commands.AuthCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.LoginCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.RegisterCommand;
import com.digitalholics.iotheraphy.security.infrastructure.token.jwt.services.JwtService;
import com.digitalholics.iotheraphy.security.domain.model.entities.Token;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.TokenRepository;
import com.digitalholics.iotheraphy.security.domain.model.valueobjects.TokenType;
import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthCommand login(LoginCommand request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var token = jwtService.getToken(user);
        var refreshToken = jwtService.getRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,token);
        return AuthCommand.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthCommand register(RegisterCommand request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(request.getRole())
                .build();

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.getToken(user);
        var refreshToken = jwtService.getRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthCommand.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user){
        var validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validTokens.isEmpty())
            return;
        validTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
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

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.getUsernameFromToken(refreshToken);
        if (username != null){
            var user = this.userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.getToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user,accessToken);
                var authResponse = AuthCommand.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
