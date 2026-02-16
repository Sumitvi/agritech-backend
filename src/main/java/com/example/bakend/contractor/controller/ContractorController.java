package com.example.bakend.contractor.controller;
import com.example.bakend.contractor.dto.AddContractorRequest;
import com.example.bakend.contractor.dto.ContractorResponse;
import com.example.bakend.contractor.entity.Contractor;
import com.example.bakend.contractor.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/contractors")
public class ContractorController {

    @Autowired
    private ContractorService contractorService;

    // Admin adds contractor
    @PostMapping
    public Contractor addContractor(@RequestBody AddContractorRequest request) {
        return contractorService.addContractor(request);
    }

    // Farmer searches contractors
    @GetMapping("/search")
    public List<ContractorResponse> search(
            @RequestParam String district,
            @RequestParam String workType
    ) {
        return contractorService.searchContractors(district, workType)
                .stream()
                .map(c -> ContractorResponse.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .mobile(c.getMobile())
                        .district(c.getDistrict())
                        .state(c.getState())
                        .workType(c.getWorkType())
                        .available(c.isAvailable())
                        .build())
                .toList();
    }

    // Admin verifies contractor
    @PostMapping("/admin/verify/{id}")
    public void verify(@PathVariable Long id) {
        contractorService.verifyContractor(id);
    }

    // Admin blocks contractor
    @PostMapping("/admin/block/{id}")
    public void block(@PathVariable Long id) {
        contractorService.blockContractor(id);
    }
}
