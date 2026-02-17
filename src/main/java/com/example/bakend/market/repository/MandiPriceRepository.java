package com.example.bakend.market.repository;

import com.example.bakend.market.entity.MandiPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MandiPriceRepository extends JpaRepository<MandiPrice, Long> {

    // 🔎 Flexible Search (Crop mandatory, State optional)
    @Query("""
        SELECT m FROM MandiPrice m
        WHERE LOWER(m.cropName) = LOWER(:cropName)
        AND (:state IS NULL OR :state = '' OR LOWER(m.state) = LOWER(:state))
    """)
    List<MandiPrice> searchMandi(
            @Param("cropName") String cropName,
            @Param("state") String state
    );

    List<MandiPrice> findByCropNameIgnoreCaseAndStateIgnoreCase(
            String cropName,
            String state
    );

    List<MandiPrice> findByCropNameIgnoreCase(String cropName);
}
