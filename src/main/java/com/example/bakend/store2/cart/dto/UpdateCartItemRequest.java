package com.example.bakend.store2.cart.dto;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long productId;
    private int quantity;
}
