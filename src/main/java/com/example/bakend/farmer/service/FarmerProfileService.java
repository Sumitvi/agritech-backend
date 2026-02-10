package com.example.bakend.farmer.service;
import com.example.bakend.farmer.entity.FarmerProfile;
import com.example.bakend.farmer.repository.FarmerProfileRepository;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmerProfileService {

    @Autowired
    private FarmerProfileRepository farmerProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public FarmerProfile createOrUpdateProfile(Long userId, FarmerProfile profileRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FarmerProfile profile = farmerProfileRepository.findByUserId(userId)
                .orElse(FarmerProfile.builder().user(user).build());

        profile.setAadhaarNumber(profileRequest.getAadhaarNumber());
        profile.setAddress(profileRequest.getAddress());
        profile.setBankAccount(profileRequest.getBankAccount());
        profile.setIfscCode(profileRequest.getIfscCode());
        profile.setVillage(profileRequest.getVillage());
        profile.setDistrict(profileRequest.getDistrict());
        profile.setState(profileRequest.getState());

        return farmerProfileRepository.save(profile);
    }

    public FarmerProfile getProfile(Long userId) {
        return farmerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Farmer profile not found"));
    }
}
