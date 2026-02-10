package com.example.bakend.store2.order.dto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private String status;
    private double totalAmount;
    private List<OrderItemResponse> items;
}
