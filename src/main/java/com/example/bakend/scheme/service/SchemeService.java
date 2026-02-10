package com.example.bakend.scheme.service;
import com.example.bakend.scheme.dto.SchemeSyncRequest;
import com.example.bakend.scheme.entity.Scheme;
import com.example.bakend.scheme.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepository schemeRepository;

    // Save scheme from API (unverified)
    public Scheme saveFromApi(SchemeSyncRequest request) {

        Scheme scheme = Scheme.builder()
                .schemeName(request.getSchemeName())
                .description(request.getDescription())
                .ministry(request.getMinistry())
                .state(request.getState())
                .crop(request.getCrop())
                .benefit(request.getBenefit())
                .eligibility(request.getEligibility())
                .applyLink(request.getApplyLink())
                .source(request.getSource())
                .verifiedByAdmin(false)
                .lastSyncedAt(LocalDateTime.now())
                .build();

        return schemeRepository.save(scheme);
    }

    // Farmer fetch schemes
    public List<Scheme> getSchemesForFarmer(String state, String crop) {
        return schemeRepository.findByStateOrState(state, "ALL");
    }
}

