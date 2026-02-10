package com.example.bakend.auth.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String mobile;
    private String password;
}
