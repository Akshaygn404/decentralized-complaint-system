package com.akshay.dcrs.dto.request.auth;

import com.akshay.dcrs.model.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
