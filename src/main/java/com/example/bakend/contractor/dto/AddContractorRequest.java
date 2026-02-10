package com.example.bakend.contractor.dto;
import lombok.Data;

@Data
public class AddContractorRequest {
    private String name;
    private String mobile;
    private String village;
    private String district;
    private String state;
    private String workType;
    private boolean available;
}

