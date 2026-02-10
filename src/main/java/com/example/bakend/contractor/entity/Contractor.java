package com.example.bakend.contractor.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String mobile;

    private String village;
    private String district;
    private String state;

    // HARVESTING, SOWING, SPRAYING, WEEDING
    private String workType;

    private boolean available;

    private boolean verified;

    private boolean active;
}

