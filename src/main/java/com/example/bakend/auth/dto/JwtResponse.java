package com.example.bakend.auth.dto;


import com.example.bakend.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private Role role;
    private Long userId;
}
