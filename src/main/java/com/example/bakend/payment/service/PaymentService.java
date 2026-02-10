package com.example.bakend.payment.service;
import com.example.bakend.payment.entity.Payment;
import com.example.bakend.payment.entity.PaymentStatus;
import com.example.bakend.payment.repository.PaymentRepository;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.entity.TradeStatus;
import com.example.bakend.trade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TradeRepository tradeRepository;

    // Initiate payment after trade SOLD
    public Payment initiatePayment(Long tradeId, Double amount) {

        Trade trade = tradeRepository.findById(tradeId)
                .orElseThrow(() -> new RuntimeException("Trade not found"));

        if (trade.getStatus() != TradeStatus.SOLD) {
            throw new RuntimeException("Payment allowed only after trade is SOLD");
        }

        Payment payment = Payment.builder()
                .trade(trade)
                .payer(trade.getTrader())
                .payee(trade.getFarmer())
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    // Update payment status
    public Payment updatePaymentStatus(Long paymentId, PaymentStatus status) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);
        payment.setUpdatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByTrader(Long traderId) {
        return paymentRepository.findByPayerId(traderId);
    }

    public List<Payment> getPaymentsByFarmer(Long farmerId) {
        return paymentRepository.findByPayeeId(farmerId);
    }
}
