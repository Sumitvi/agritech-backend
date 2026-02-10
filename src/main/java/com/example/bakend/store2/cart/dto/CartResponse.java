package com.example.bakend.store2.cart.dto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> items;
    private double totalAmount;
}
