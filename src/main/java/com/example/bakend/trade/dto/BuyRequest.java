package com.example.bakend.trade.dto;
import lombok.Data;

@Data
public class BuyRequest {
    private Long tradeId;
    private Long traderId;
    private Double offeredRate;
}
