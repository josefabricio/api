package com.digitalholics.iotheraphy.security.interfaces.rest;

import com.digitalholics.iotheraphy.security.infrastructure.authorization.services.AuthService;
import com.digitalholics.iotheraphy.security.domain.model.commands.AuthCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.LoginCommand;
import com.digitalholics.iotheraphy.security.domain.model.commands.RegisterCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping(value = "authentication")
    public ResponseEntity<AuthCommand> login(
            @RequestBody LoginCommand request
    ){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value="registration")
    public ResponseEntity<AuthCommand> register(
            @RequestBody RegisterCommand request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(value = "/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request,response);
    }


}
