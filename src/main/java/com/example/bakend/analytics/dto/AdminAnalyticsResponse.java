package com.example.bakend.analytics.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminAnalyticsResponse {

    private long totalUsers;
    private long totalFarmers;
    private long totalTraders;

    private long totalTrades;
    private double totalTradeValue;

    private long successfulPayments;
    private double totalRevenue;
}
