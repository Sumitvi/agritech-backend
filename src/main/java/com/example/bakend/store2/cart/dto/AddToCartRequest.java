package com.example.bakend.store2.cart.dto;
import lombok.Data;

@Data
public class AddToCartRequest {
    private Long productId;
    private int quantity;
}

