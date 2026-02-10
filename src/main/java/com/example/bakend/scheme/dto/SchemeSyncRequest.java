package com.example.bakend.scheme.dto;
import lombok.Data;

@Data
public class SchemeSyncRequest {

    private String schemeName;
    private String description;
    private String ministry;
    private String state;
    private String crop;
    private String benefit;
    private String eligibility;
    private String applyLink;
    private String source; // DATA_GOV_IN
}
