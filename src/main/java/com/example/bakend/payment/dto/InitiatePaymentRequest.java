package com.example.bakend.payment.dto;
import lombok.Data;

@Data
public class InitiatePaymentRequest {
    private Long tradeId;
    private Double amount;
}
