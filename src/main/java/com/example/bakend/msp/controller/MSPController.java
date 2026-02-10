package com.example.bakend.msp.controller;


import com.example.bakend.msp.dto.MSPRequest;
import com.example.bakend.msp.entity.MSP;
import com.example.bakend.msp.service.MSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/msp")
public class MSPController {

    @Autowired
    private MSPService mspService;

    // Add / Update MSP (Admin / Govt)
    @PostMapping("/add")
    public ResponseEntity<MSP> addMSP(@RequestBody MSP msp) {
        MSP saved = mspService.saveMSP(msp);
        return ResponseEntity.ok(saved);
    }

    // Get MSP for a crop (Farmer / Trader)
    @PostMapping("/get")
    public ResponseEntity<MSP> getMSP(@RequestBody MSPRequest request) {
        MSP msp = mspService.getMSP(request.getCropName());
        return ResponseEntity.ok(msp);
    }
}
