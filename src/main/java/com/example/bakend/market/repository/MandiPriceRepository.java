package com.example.bakend.market.repository;
import com.example.bakend.market.entity.MandiPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MandiPriceRepository extends JpaRepository<MandiPrice, Long> {

    List<MandiPrice> findByCropNameAndState(String cropName, String state);

    List<MandiPrice> findByCropNameIgnoreCaseAndStateIgnoreCase(
            String cropName,
            String state
    );
}
