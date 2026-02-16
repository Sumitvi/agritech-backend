package com.example.bakend.market.controller;

import com.example.bakend.market.dto.MandiSearchRequest;
import com.example.bakend.market.entity.MandiPrice;
import com.example.bakend.market.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    // 🔎 Search mandi prices (Farmer / Trader)
    @PostMapping("/mandi")
    public ResponseEntity<List<MandiPrice>> getMandiPrices(
            @RequestBody MandiSearchRequest request) {

        List<MandiPrice> prices =
                marketService.getMandiPrices(
                        request.getCropName(),
                        request.getState()
                );

        return ResponseEntity.ok(prices);
    }

    // ➕ Add mandi price (Admin / Govt)
    @PostMapping("/mandi/add")
    public ResponseEntity<MandiPrice> addMandiPrice(
            @RequestBody MandiPrice mandiPrice) {

        return ResponseEntity.ok(
                marketService.saveMandiPrice(mandiPrice)
        );
    }

    // 📋 Get all mandi prices (Admin)
    @GetMapping("/mandi/all")
    public ResponseEntity<List<MandiPrice>> getAllMandi() {
        return ResponseEntity.ok(
                marketService.getAllMandiPrices()
        );
    }

    // ✏ Update mandi price (Admin)
    @PutMapping("/mandi/update/{id}")
    public ResponseEntity<MandiPrice> updateMandiPrice(
            @PathVariable Long id,
            @RequestBody MandiPrice mandiPrice) {

        return ResponseEntity.ok(
                marketService.updateMandiPrice(id, mandiPrice)
        );
    }

    // ❌ Delete mandi price (Admin)
    @DeleteMapping("/mandi/delete/{id}")
    public ResponseEntity<String> deleteMandiPrice(
            @PathVariable Long id) {

        marketService.deleteMandiPrice(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
