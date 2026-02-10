package com.example.bakend.trade.dto;
import lombok.Data;

@Data
public class SellRequest {
    private Long farmerId;
    private Long cropId;
    private Double quantityQuintal;
    private Double expectedRate;
}
