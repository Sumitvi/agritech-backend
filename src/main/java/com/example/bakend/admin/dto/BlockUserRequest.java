package com.example.bakend.admin.dto;
import lombok.Data;

@Data
public class BlockUserRequest {
    private Long userId;
    private boolean active;
}
