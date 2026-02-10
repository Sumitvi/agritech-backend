package com.example.bakend.land.controller;
import com.example.bakend.land.entity.Land;
import com.example.bakend.land.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farmers")
public class LandController {

    @Autowired
    private LandService landService;

    // Add land for a farmer
    @PostMapping("/{farmerId}/lands")
    public ResponseEntity<Land> addLand(@PathVariable Long farmerId,
                                        @RequestBody Land landRequest) {

        Land savedLand = landService.addLand(farmerId, landRequest);
        return ResponseEntity.ok(savedLand);
    }

    // Get all lands of a farmer
    @GetMapping("/{farmerId}/lands")
    public ResponseEntity<List<Land>> getLands(@PathVariable Long farmerId) {

        List<Land> lands = landService.getLandsByFarmer(farmerId);
        return ResponseEntity.ok(lands);
    }
}
