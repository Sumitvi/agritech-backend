package com.example.bakend.farmer.controller;
import com.example.bakend.farmer.entity.FarmerProfile;
import com.example.bakend.farmer.service.FarmerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/farmers")
public class FarmerProfileController {

    @Autowired
    private FarmerProfileService farmerProfileService;


    @PostMapping("/profile/{userId}")
    public ResponseEntity<FarmerProfile> createOrUpdateProfile(
            @PathVariable Long userId,
            @RequestBody FarmerProfile profileRequest) {

        FarmerProfile savedProfile = farmerProfileService.createOrUpdateProfile(userId, profileRequest);
        return ResponseEntity.ok(savedProfile);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<FarmerProfile> getProfile(@PathVariable Long userId) {
        FarmerProfile profile = farmerProfileService.getProfile(userId);
        return ResponseEntity.ok(profile);
    }
}
