package com.example.bakend.admin.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashboardResponse {

    private long totalUsers;
    private long verifiedUsers;
    private long farmers;
    private long traders;

    private long totalTrades;
    private long soldTrades;

    private long totalPayments;
    private long successfulPayments;
}
