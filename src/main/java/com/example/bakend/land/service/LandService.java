package com.example.bakend.land.service;
import com.example.bakend.land.entity.Land;
import com.example.bakend.land.repository.LandRepository;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandService {

    @Autowired
    private LandRepository landRepository;

    @Autowired
    private UserRepository userRepository;

    // Add new land for a farmer
    public Land addLand(Long farmerId, Land landRequest) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        landRequest.setFarmer(farmer);
        return landRepository.save(landRequest);
    }

    // Get all lands of a farmer
    public List<Land> getLandsByFarmer(Long farmerId) {
        return landRepository.findByFarmerId(farmerId);
    }
}
