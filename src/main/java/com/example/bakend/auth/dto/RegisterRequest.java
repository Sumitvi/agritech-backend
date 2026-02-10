package com.example.bakend.auth.dto;


import com.example.bakend.user.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String mobile;
    private String email;
    private String password;
    private Role role;   // ROLE_FARMER, ROLE_TRADER, ROLE_ADMIN, ROLE_GOVT
}
