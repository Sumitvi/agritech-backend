package com.example.bakend.scheme.controller;

import com.example.bakend.scheme.dto.SchemeFilterRequest;
import com.example.bakend.scheme.dto.SchemeSyncRequest;
import com.example.bakend.scheme.entity.Scheme;
import com.example.bakend.scheme.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schemes")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    // API sync endpoint (cron / external fetch)
    @PostMapping("/sync")
    public ResponseEntity<Scheme> syncSchemeFromApi(
            @RequestBody SchemeSyncRequest request) {

        Scheme scheme = schemeService.saveFromApi(request);
        return ResponseEntity.ok(scheme);
    }

    // Farmer fetch schemes (JSON based)
    @PostMapping("/farmer")
    public ResponseEntity<List<Scheme>> getSchemesForFarmer(
            @RequestBody SchemeFilterRequest request) {

        List<Scheme> schemes =
                schemeService.getSchemesForFarmer(
                        request.getState(),
                        request.getCrop()
                );

        return ResponseEntity.ok(schemes);
    }
}
