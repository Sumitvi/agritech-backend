package com.example.bakend.store2.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}
