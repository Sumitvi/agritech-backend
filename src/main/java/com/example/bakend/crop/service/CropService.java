package com.example.bakend.crop.service;
import com.example.bakend.crop.entity.Crop;
import com.example.bakend.crop.entity.CropStatus;
import com.example.bakend.crop.repository.CropRepository;
import com.example.bakend.land.entity.Land;
import com.example.bakend.land.repository.LandRepository;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LandRepository landRepository;

    // Sow a new crop
    public Crop sowCrop(Long farmerId, Long landId, Crop cropRequest) {

        User farmer = userRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        Land land = landRepository.findById(landId)
                .orElseThrow(() -> new RuntimeException("Land not found"));

        cropRequest.setFarmer(farmer);
        cropRequest.setLand(land);
        cropRequest.setStatus(CropStatus.SOWN);

        return cropRepository.save(cropRequest);
    }

    // Get all crops of a farmer
    public List<Crop> getCropsByFarmer(Long farmerId) {
        return cropRepository.findByFarmerId(farmerId);
    }

    // Harvest a crop
    public Crop harvestCrop(Long cropId, Double actualYield) {

        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new RuntimeException("Crop not found"));

        crop.setActualHarvestDate(LocalDate.now());
        crop.setActualYieldQuintal(actualYield);
        crop.setStatus(CropStatus.HARVESTED);

        return cropRepository.save(crop);
    }
}
