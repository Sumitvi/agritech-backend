package com.example.bakend.land.entity;
import com.example.bakend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which farmer owns this land
    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    private Double areaInAcre;

    private String soilType;        // Black, Red, Alluvial, etc.
    private String irrigationType; // Canal, Borewell, Rainfed, Drip

    private String village;
    private String district;
    private String state;
}
