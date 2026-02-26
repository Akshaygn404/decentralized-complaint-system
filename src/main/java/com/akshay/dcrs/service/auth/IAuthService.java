package com.akshay.dcrs.service.auth;

import com.akshay.dcrs.dto.request.auth.LoginRequest;
import com.akshay.dcrs.dto.request.auth.RegisterRequest;
import com.akshay.dcrs.dto.response.auth.AuthResponse;

public interface IAuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}