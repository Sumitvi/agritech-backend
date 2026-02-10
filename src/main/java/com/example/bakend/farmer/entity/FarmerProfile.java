package com.example.bakend.farmer.entity;
import com.example.bakend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farmer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String aadhaarNumber;
    private String address;
    private String bankAccount;
    private String ifscCode;
    private String village;
    private String district;
    private String state;
}
