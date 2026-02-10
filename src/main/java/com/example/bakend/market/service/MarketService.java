package com.example.bakend.market.service;
import com.example.bakend.market.entity.MandiPrice;
import com.example.bakend.market.repository.MandiPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    @Autowired
    private MandiPriceRepository mandiPriceRepository;

    // Get mandi prices by crop & state
    public List<MandiPrice> getMandiPrices(String cropName, String state) {
        return mandiPriceRepository.findByCropNameAndState(cropName, state);
    }

    // Add or update mandi price (for admin / govt)
    public MandiPrice saveMandiPrice(MandiPrice mandiPrice) {
        return mandiPriceRepository.save(mandiPrice);
    }
}
