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
                .available(true)
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


    public List<Contractor> getAll() {
        return contractorRepository.findAll();
    }

    // Search
    public List<Contractor> search(String keyword) {
        return contractorRepository
                .findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCase(
                        keyword, keyword);
    }

    // Update
    public Contractor update(Long id, Contractor updated) {
        Contractor existing = contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contractor not found"));

        existing.setName(updated.getName());
        existing.setMobile(updated.getMobile());
        existing.setDistrict(updated.getDistrict());
        existing.setState(updated.getState());
        existing.setWorkType(updated.getWorkType());

        return contractorRepository.save(existing);
    }

    // Delete
    public void delete(Long id) {
        contractorRepository.deleteById(id);
    }

    // Verify
    public Contractor verify(Long id) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        contractor.setVerified(true);
        return contractorRepository.save(contractor);
    }

    // Block / Unblock
    public Contractor block(Long id, boolean active) {
        Contractor contractor = contractorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        contractor.setActive(active);
        return contractorRepository.save(contractor);
    }
}
