package com.example.bakend.msp.service;


import com.example.bakend.msp.MSPRepository.MSPRepository;
import com.example.bakend.msp.entity.MSP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

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
}
