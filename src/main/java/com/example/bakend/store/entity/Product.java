package com.example.bakend.store.entity;
import com.example.bakend.store.dto.SellerType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Urea, Wheat Seed
    private String category;        // SEED, FERTILIZER, TOOL
    private String description;

    private double price;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private SellerType sellerType;  // PLATFORM / VENDOR / COOPERATIVE

    // Future use (nullable for PLATFORM)
    private Long sellerId;           // vendorId / cooperativeId

    private boolean active = true;
}
