package com.example.bakend.payment.dto;
import com.example.bakend.payment.entity.PaymentStatus;
import lombok.Data;

@Data
public class UpdatePaymentStatusRequest {
    private Long paymentId;
    private PaymentStatus status;
}
