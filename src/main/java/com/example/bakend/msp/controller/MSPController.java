package com.example.bakend.msp.controller;


import com.example.bakend.msp.dto.MSPRequest;
import com.example.bakend.msp.entity.MSP;
import com.example.bakend.msp.service.MSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/all")
    public ResponseEntity<List<MSP>> getAllMSP() {
        return ResponseEntity.ok(mspService.getAllMSP());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<MSP> updateMSP(
            @PathVariable Long id,
            @RequestBody MSP updatedMSP) {

        return ResponseEntity.ok(mspService.updateMSP(id, updatedMSP));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMSP(@PathVariable Long id) {
        mspService.deleteMSP(id);
        return ResponseEntity.ok("MSP deleted successfully");
    }
}
