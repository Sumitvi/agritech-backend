package com.example.bakend.scheme.dto;

import lombok.Data;

@Data
public class SchemeFilterRequest {
    private String state;
    private String crop;
}
