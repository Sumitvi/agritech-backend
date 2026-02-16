package com.example.bakend.crop.controller;
import com.example.bakend.crop.entity.Crop;
import com.example.bakend.crop.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    // Sow a new crop
    @PostMapping("/sow")
    public ResponseEntity<Crop> sowCrop(@RequestParam Long farmerId,
                                        @RequestParam Long landId,
                                        @RequestBody Crop cropRequest) {

        Crop savedCrop = cropService.sowCrop(farmerId, landId, cropRequest);
        return ResponseEntity.ok(savedCrop);
    }

    // Get all crops of a farmer
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Crop>> getCropsByFarmer(@PathVariable Long farmerId) {

        List<Crop> crops = cropService.getCropsByFarmer(farmerId);
        return ResponseEntity.ok(crops);
    }

    // Harvest a crop
    @PutMapping("/{cropId}/harvest")
    public ResponseEntity<Crop> harvestCrop(@PathVariable Long cropId,
                                            @RequestParam Double actualYieldQuintal) {

        Crop harvestedCrop = cropService.harvestCrop(cropId, actualYieldQuintal);
        return ResponseEntity.ok(harvestedCrop);
    }
}
