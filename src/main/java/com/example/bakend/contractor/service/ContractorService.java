package com.example.bakend.contractor.service;
import com.example.bakend.contractor.dto.AddContractorRequest;
import com.example.bakend.contractor.entity.Contractor;
import com.example.bakend.contractor.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    // Admin adds contractor
    public Contractor addContractor(AddContractorRequest request) {

        Contractor contractor = Contractor.builder()
                .name(request.getName())
                .mobile(request.getMobile())
                .village(request.getVillage())
                .district(request.getDistrict())
                .state(request.getState())
                .workType(request.getWorkType())
                .available(request.isAvailable())
                .verified(false)
                .active(true)
                .build();

        return contractorRepository.save(contractor);
    }

    // Farmer search
    public List<Contractor> searchContractors(String district, String workType) {
        return contractorRepository
                .findByDistrictAndWorkTypeAndAvailableTrueAndVerifiedTrueAndActiveTrue(
                        district, workType
                );
    }

    // Admin verify contractor
    public void verifyContractor(Long contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId)
                .orElseThrow(() -> new RuntimeException("Contractor not found"));

        contractor.setVerified(true);
        contractorRepository.save(contractor);
    }

    // Admin block contractor
    public void blockContractor(Long contractorId) {
        Contractor contractor = contractorRepository.findById(contractorId)
                .orElseThrow(() -> new RuntimeException("Contractor not found"));

        contractor.setActive(false);
        contractorRepository.save(contractor);
    }
}
