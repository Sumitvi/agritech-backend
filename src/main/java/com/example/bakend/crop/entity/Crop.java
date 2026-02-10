package com.example.bakend.crop.entity;
import com.example.bakend.land.entity.Land;
import com.example.bakend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "crops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which farmer owns this crop
    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    // On which land this crop is grown
    @ManyToOne
    @JoinColumn(name = "land_id", nullable = false)
    private Land land;

    private String cropName;          // Wheat, Rice, Maize
    private LocalDate sowingDate;
    private LocalDate expectedHarvestDate;
    private LocalDate actualHarvestDate;

    private Double expectedYieldQuintal;
    private Double actualYieldQuintal;

    @Enumerated(EnumType.STRING)
    private CropStatus status;        // SOWN, GROWING, HARVESTED
}
