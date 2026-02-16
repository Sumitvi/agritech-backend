package com.example.bakend.msp.service;


import com.example.bakend.msp.MSPRepository.MSPRepository;
import com.example.bakend.msp.entity.MSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class MSPService {

    @Autowired
    private MSPRepository mspRepository;

    // Add or update MSP (Admin / Govt)
    public MSP saveMSP(MSP msp) {
        return mspRepository.save(msp);
    }

    // Get MSP for crop (current year)
    public MSP getMSP(String cropName) {
        int currentYear = Year.now().getValue();
        return mspRepository.findByCropNameAndYear(cropName, currentYear)
                .orElseThrow(() -> new RuntimeException("MSP not found for crop: " + cropName));
    }

    public List<MSP> getAllMSP() {
        return mspRepository.findAll();
    }

    public MSP updateMSP(Long id, MSP updated) {
        MSP existing = mspRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MSP not found"));

        existing.setCropName(updated.getCropName());
        existing.setMspPrice(updated.getMspPrice());

        return mspRepository.save(existing);
    }

    public void deleteMSP(Long id) {
        mspRepository.deleteById(id);
    }
}
