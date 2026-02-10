package com.example.bakend.scheme.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "schemes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schemeName;
    private String description;

    private String ministry;      // Agriculture, Rural Development
    private String state;         // ALL, UP, MH etc.
    private String crop;          // Wheat, Rice, ALL

    private String benefit;       // ₹6000/year, Insurance cover
    private String eligibility;   // Text (API-based for now)

    private String applyLink;      // Official portal link

    private String source;         // DATA_GOV_IN, STATE_PORTAL, MANUAL

    private boolean verifiedByAdmin; // false for now

    private LocalDateTime lastSyncedAt;
}
