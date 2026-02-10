package com.example.bakend.market.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mandi_prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MandiPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;      // Wheat, Rice, Maize
    private String mandiName;     // Varanasi, Indore
    private String district;
    private String state;

    private Double minPrice;      // ₹/quintal
    private Double maxPrice;      // ₹/quintal
    private Double modalPrice;    // ₹/quintal

    private LocalDate priceDate;
}
