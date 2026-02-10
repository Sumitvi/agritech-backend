package com.example.bakend.analytics.controller;
import com.example.bakend.analytics.dto.AdminAnalyticsResponse;
import com.example.bakend.analytics.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    // Admin / Govt analytics
    @GetMapping("/admin")
    public ResponseEntity<AdminAnalyticsResponse> adminAnalytics() {
        return ResponseEntity.ok(analyticsService.getAdminAnalytics());
    }
}
