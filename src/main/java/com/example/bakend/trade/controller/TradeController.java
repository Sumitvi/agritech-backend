package com.example.bakend.trade.controller;

import com.example.bakend.trade.dto.BuyRequest;
import com.example.bakend.trade.dto.SellRequest;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    // Farmer lists crop for sale
    @PostMapping("/sell")
    public ResponseEntity<Trade> sellCrop(@RequestBody SellRequest request) {

        Trade trade = tradeService.sellCrop(
                request.getFarmerId(),
                request.getCropId(),
                request.getQuantityQuintal(),
                request.getExpectedRate()
        );

        return ResponseEntity.ok(trade);
    }

    // Trader buys a listed crop
    @PostMapping("/buy")
    public ResponseEntity<Trade> buyCrop(@RequestBody BuyRequest request) {

        Trade trade = tradeService.buyCrop(
                request.getTradeId(),
                request.getTraderId(),
                request.getOfferedRate()
        );

        return ResponseEntity.ok(trade);
    }

    // Get all trades of a farmer
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Trade>> getFarmerTrades(@PathVariable Long farmerId) {

        List<Trade> trades = tradeService.getTradesByFarmer(farmerId);
        return ResponseEntity.ok(trades);
    }

    // Get all trades of a trader
    @GetMapping("/trader/{traderId}")
    public ResponseEntity<List<Trade>> getTraderTrades(@PathVariable Long traderId) {

        List<Trade> trades = tradeService.getTradesByTrader(traderId);
        return ResponseEntity.ok(trades);
    }
}
