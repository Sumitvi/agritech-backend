package com.example.bakend.payment.entity;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Linked trade
    @OneToOne
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

    // Trader pays
    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    // Farmer receives
    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
