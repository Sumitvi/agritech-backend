package com.example.bakend.crop.repository;
import com.example.bakend.crop.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByFarmerId(Long farmerId);
}
