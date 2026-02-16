package com.example.bakend.admin.controller;
import com.example.bakend.admin.dto.AdminDashboardResponse;
import com.example.bakend.admin.dto.BlockUserRequest;
import com.example.bakend.admin.dto.VerifyUserRequest;
import com.example.bakend.admin.service.AdminService;
import com.example.bakend.payment.entity.Payment;
import com.example.bakend.payment.repository.PaymentRepository;
import com.example.bakend.scheme.entity.Scheme;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.repository.TradeRepository;
import com.example.bakend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Verify Farmer / Trader
    @PostMapping("/verify-user")
    public ResponseEntity<User> verifyUser(
            @RequestBody VerifyUserRequest request) {

        User user =
                adminService.verifyUser(
                        request.getUserId(),
                        request.isVerified()
                );

        return ResponseEntity.ok(user);
    }

    // View all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PostMapping("/verify-scheme")
    public ResponseEntity<Scheme> verifyScheme(
            @RequestParam Long schemeId,
            @RequestParam boolean verified) {

        return ResponseEntity.ok(
                adminService.verifyScheme(schemeId, verified)
        );
    }

    @Autowired
    private TradeRepository tradeRepository;

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getAllTrades() {
        return ResponseEntity.ok(tradeRepository.findAll());
    }


    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardResponse> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboardMetrics());
    }


    @PostMapping("/block-user")
    public ResponseEntity<User> blockUser(
            @RequestBody BlockUserRequest request) {

        return ResponseEntity.ok(
                adminService.blockOrUnblockUser(
                        request.getUserId(),
                        request.isActive()
                )
        );
    }

}

