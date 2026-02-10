package com.example.bakend.store.dto;
import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private String category;
    private String description;
    private double price;
    private int stockQuantity;
}
