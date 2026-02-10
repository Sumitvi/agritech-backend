package com.example.bakend.contractor.repository;
import com.example.bakend.contractor.entity.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

    List<Contractor> findByDistrictAndWorkTypeAndAvailableTrueAndVerifiedTrueAndActiveTrue(
            String district,
            String workType
    );
}
