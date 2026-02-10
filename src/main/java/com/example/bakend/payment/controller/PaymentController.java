package com.example.bakend.payment.controller;
import com.example.bakend.payment.dto.InitiatePaymentRequest;
import com.example.bakend.payment.dto.UpdatePaymentStatusRequest;
import com.example.bakend.payment.entity.Payment;
import com.example.bakend.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Trader initiates payment
    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(
            @RequestBody InitiatePaymentRequest request) {

        Payment payment =
                paymentService.initiatePayment(
                        request.getTradeId(),
                        request.getAmount()
                );

        return ResponseEntity.ok(payment);
    }

    // Update payment status (Admin / System)
    @PostMapping("/update-status")
    public ResponseEntity<Payment> updateStatus(
            @RequestBody UpdatePaymentStatusRequest request) {

        Payment payment =
                paymentService.updatePaymentStatus(
                        request.getPaymentId(),
                        request.getStatus()
                );

        return ResponseEntity.ok(payment);
    }

    // Trader payment history
    @GetMapping("/trader/{traderId}")
    public ResponseEntity<List<Payment>> traderPayments(
            @PathVariable Long traderId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByTrader(traderId)
        );
    }

    // Farmer payment history
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Payment>> farmerPayments(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByFarmer(farmerId)
        );
    }
}
