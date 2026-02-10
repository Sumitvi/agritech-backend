package com.example.bakend.land.repository;
import com.example.bakend.land.entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandRepository extends JpaRepository<Land, Long> {

    List<Land> findByFarmerId(Long farmerId);
}
