package com.akshay.dcrs.controller.auth;

import com.akshay.dcrs.dto.request.auth.LoginRequest;
import com.akshay.dcrs.dto.request.auth.RegisterRequest;
import com.akshay.dcrs.dto.response.auth.AuthResponse;
import com.akshay.dcrs.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}