package com.digitalholics.iotheraphy.security.infrastructure.authorization.configuratuion;

import com.digitalholics.iotheraphy.security.infrastructure.token.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.digitalholics.iotheraphy.security.domain.model.valueobjects.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("**").permitAll()
                                .requestMatchers("/api/v1/**").hasAnyRole(ADMIN.name(),USER.name(),PATIENT.name(),PHYSIOTHERAPIST.name())
                                .requestMatchers(GET,"/api/v1/**").hasAnyAuthority(ADMIN.name(),USER.name(),PATIENT.name(),PHYSIOTHERAPIST.name())
                                .requestMatchers(POST,"/api/v1/**").hasAnyAuthority(ADMIN.name(),USER.name(),PATIENT.name(),PHYSIOTHERAPIST.name())
                                .requestMatchers(PUT,"/api/v1/**").hasAnyAuthority(ADMIN.name(),USER.name(),PATIENT.name(),PHYSIOTHERAPIST.name())
                                .requestMatchers(DELETE,"/api/v1/**").hasAnyAuthority(ADMIN.name(),USER.name(),PATIENT.name(),PHYSIOTHERAPIST.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout.logoutUrl("/api/v1/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) ->
                                SecurityContextHolder.clearContext())
                )
                .build();
    }
}
