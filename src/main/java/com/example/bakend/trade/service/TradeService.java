package com.example.bakend.trade.service;
import com.example.bakend.crop.entity.Crop;
import com.example.bakend.crop.repository.CropRepository;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.entity.TradeStatus;
import com.example.bakend.trade.repository.TradeRepository;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CropRepository cropRepository;

    // Farmer lists crop for sale
    public Trade sellCrop(Long farmerId, Long cropId, Double quantityQuintal, Double expectedRate) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        Trade trade = Trade.builder()
                .farmer(farmer)
                .crop(crop)
                .quantityQuintal(quantityQuintal)
                .expectedRate(expectedRate)
                .status(TradeStatus.LISTED)
                .createdAt(LocalDateTime.now())
                .build();

        return tradeRepository.save(trade);
    }

    // Trader buys a listed crop
    public Trade buyCrop(Long tradeId, Long traderId, Double offeredRate) {

        Trade trade = tradeRepository.findById(tradeId)
                .orElseThrow(() -> new RuntimeException("Trade not found"));

        User trader = userRepository.findById(traderId)
                .orElseThrow(() -> new RuntimeException("Trader not found"));

        trade.setTrader(trader);
        trade.setFinalRate(offeredRate);
        trade.setStatus(TradeStatus.SOLD);

        return tradeRepository.save(trade);
    }

    // Get all trades of a farmer
    public List<Trade> getTradesByFarmer(Long farmerId) {
        return tradeRepository.findByFarmerId(farmerId);
    }

    public List<Trade> getAllListedTrades() {
        return tradeRepository.findByStatus(TradeStatus.LISTED);

    }
    // Get all trades of a trader
    public List<Trade> getTradesByTrader(Long traderId) {
        return tradeRepository.findByTraderId(traderId);
    }
}

