package com.example.bakend.admin.dto;
import lombok.Data;

@Data
public class VerifyUserRequest {
    private Long userId;
    private boolean verified;
}
