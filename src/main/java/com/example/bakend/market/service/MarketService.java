package com.example.bakend.market.service;

import com.example.bakend.market.entity.MandiPrice;
import com.example.bakend.market.repository.MandiPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MarketService {

    @Autowired
    private MandiPriceRepository mandiPriceRepository;

    // 🔎 Get mandi prices (Crop mandatory, State optional)
    public List<MandiPrice> getMandiPrices(String cropName, String state) {

        if (cropName == null || cropName.trim().isEmpty()) {
            throw new RuntimeException("Crop name is required");
        }

        cropName = cropName.trim();

        // If state is provided
        if (state != null && !state.trim().isEmpty()) {
            return mandiPriceRepository
                    .findByCropNameIgnoreCaseAndStateIgnoreCase(
                            cropName,
                            state.trim()
                    );
        }

        // If state not provided → return all states for crop
        return mandiPriceRepository
                .findByCropNameIgnoreCase(cropName);
    }

    // ➕ Add mandi price (Admin/Govt)
    public MandiPrice saveMandiPrice(MandiPrice mandiPrice) {

        if (mandiPrice.getCropName() == null || mandiPrice.getCropName().isBlank()) {
            throw new RuntimeException("Crop name is required");
        }

        if (mandiPrice.getModalPrice() == null) {
            throw new RuntimeException("Modal price is required");
        }

        // Trim strings
        mandiPrice.setCropName(mandiPrice.getCropName().trim());
        if (mandiPrice.getState() != null)
            mandiPrice.setState(mandiPrice.getState().trim());

        if (mandiPrice.getMandiName() != null)
            mandiPrice.setMandiName(mandiPrice.getMandiName().trim());

        // Auto-set fields
        mandiPrice.setPriceDate(LocalDate.now());
        mandiPrice.setMinPrice(mandiPrice.getModalPrice());
        mandiPrice.setMaxPrice(mandiPrice.getModalPrice());

        return mandiPriceRepository.save(mandiPrice);
    }

    // 📋 Get all mandi prices
    public List<MandiPrice> getAllMandiPrices() {
        return mandiPriceRepository.findAll();
    }

    // ✏ Update mandi price
    public MandiPrice updateMandiPrice(Long id, MandiPrice updated) {

        MandiPrice existing = mandiPriceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mandi price not found"));

        existing.setCropName(updated.getCropName().trim());
        existing.setState(updated.getState() != null ? updated.getState().trim() : null);
        existing.setMandiName(updated.getMandiName() != null ? updated.getMandiName().trim() : null);
        existing.setModalPrice(updated.getModalPrice());

        // Auto-update price values
        existing.setMinPrice(updated.getModalPrice());
        existing.setMaxPrice(updated.getModalPrice());
        existing.setPriceDate(LocalDate.now());

        return mandiPriceRepository.save(existing);
    }

    // ❌ Delete mandi price
    public void deleteMandiPrice(Long id) {

        if (!mandiPriceRepository.existsById(id)) {
            throw new RuntimeException("Mandi price not found");
        }

        mandiPriceRepository.deleteById(id);
    }
}
