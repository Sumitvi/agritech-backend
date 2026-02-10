package com.example.bakend.trade.entity;
import com.example.bakend.crop.entity.Crop;
import com.example.bakend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Farmer who is selling
    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    // Trader who buys (null until matched)
    @ManyToOne
    @JoinColumn(name = "trader_id")
    private User trader;

    // Crop being sold
    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    private Double quantityQuintal;
    private Double expectedRate;   // Farmer expected price
    private Double finalRate;      // Agreed price

    @Enumerated(EnumType.STRING)
    private TradeStatus status;

    private LocalDateTime createdAt;
}
