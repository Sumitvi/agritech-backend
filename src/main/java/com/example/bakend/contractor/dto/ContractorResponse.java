package com.example.bakend.contractor.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractorResponse {
    private Long id;
    private String name;
    private String mobile;
    private String district;
    private String state;
    private String workType;
    private boolean available;
}
