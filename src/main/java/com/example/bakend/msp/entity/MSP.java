package com.example.bakend.msp.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "msp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MSP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;     // Wheat, Rice, Maize
    private Integer year;        // 2025, 2026
    private Double mspPrice;     // ₹ per quintal
}

