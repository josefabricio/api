package com.digitalholics.iotheraphy.Security.Auth;

import com.digitalholics.iotheraphy.Security.Domain.Service.Communication.AuthResponse;
import com.digitalholics.iotheraphy.Security.Domain.Service.Communication.LoginRequest;
import com.digitalholics.iotheraphy.Security.Domain.Service.Communication.RegisterRequest;
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
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value="registration")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
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
