package com.example.bakend.msp.MSPRepository;
import com.example.bakend.msp.entity.MSP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MSPRepository extends JpaRepository<MSP, Long> {

    Optional<MSP> findByCropNameAndYear(String cropName, Integer year);
}
