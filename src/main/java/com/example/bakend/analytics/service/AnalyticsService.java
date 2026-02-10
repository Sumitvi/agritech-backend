package com.example.bakend.analytics.service;
import com.example.bakend.analytics.dto.AdminAnalyticsResponse;
import com.example.bakend.payment.entity.Payment;
import com.example.bakend.payment.entity.PaymentStatus;
import com.example.bakend.payment.repository.PaymentRepository;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.repository.TradeRepository;
import com.example.bakend.user.entity.Role;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public AdminAnalyticsResponse getAdminAnalytics() {

        List<Trade> trades = tradeRepository.findAll();
        List<Payment> payments =
                paymentRepository.findByStatus(PaymentStatus.SUCCESS);

        double totalTradeValue = trades.stream()
                .filter(t -> t.getFinalRate() != null)
                .mapToDouble(t -> t.getFinalRate() * t.getQuantityQuintal())
                .sum();

        double totalRevenue = payments.stream()
                .mapToDouble(Payment::getAmount)
                .sum();

        return AdminAnalyticsResponse.builder()
                .totalUsers(userRepository.count())
                .totalFarmers(userRepository.countByRole(Role.ROLE_FARMER))
                .totalTraders(userRepository.countByRole(Role.ROLE_TRADER))
                .totalTrades(trades.size())
                .totalTradeValue(totalTradeValue)
                .successfulPayments(payments.size())
                .totalRevenue(totalRevenue)
                .build();
    }
}

