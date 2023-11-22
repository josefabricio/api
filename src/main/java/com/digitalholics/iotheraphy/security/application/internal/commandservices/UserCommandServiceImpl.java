package com.digitalholics.iotheraphy.security.application.internal.commandservices;

import com.digitalholics.iotheraphy.security.domain.model.commands.LoginCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.RegisterCommand;
import com.digitalholics.iotheraphy.security.domain.services.UserCommandService;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.TokenRepository;
import com.digitalholics.iotheraphy.security.infrastructure.persistance.jpa.repositories.UserRepository;
import com.digitalholics.iotheraphy.security.infrastructure.token.jwt.services.JwtService;
import com.digitalholics.iotheraphy.security.interfaces.rest.resources.UserResource;
import com.stripe.service.issuing.TokenService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.digitalholics.iotheraphy.security.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private String token;
    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return null;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    };

    public UserCommandServiceImpl(UserRepository userRepository, JwtService tokenRepository) {
        this.userRepository = userRepository;
        this.jwtService = tokenRepository;
    }


    @Override
    public Optional<User> handle(RegisterCommand command) {
        if (userRepository.findByUsername(command.getUsername()) != null) throw new RuntimeException("User already exists");

        User user = User.builder()
                .username(command.getUsername())
                .password(passwordEncoder.encode(command.getPassword()))
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .role(command.getRole())
                .build();
        token = userRepository.save(user).getTokens().toString();
        return userRepository.findByUsername(command.getUsername());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(LoginCommand command) {
        var user = userRepository.findByUsername(command.getUsername());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!passwordEncoder.matches(command.getPassword(), user.get().getPassword())) throw new RuntimeException("Invalid password");
        return Optional.of(new ImmutablePair<>(user.get(), token));
    }
}
