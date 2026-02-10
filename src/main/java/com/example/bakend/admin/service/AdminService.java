package com.example.bakend.admin.service;
import com.example.bakend.admin.dto.AdminDashboardResponse;
import com.example.bakend.payment.repository.PaymentRepository;
import com.example.bakend.scheme.entity.Scheme;
import com.example.bakend.scheme.repository.SchemeRepository;
import com.example.bakend.trade.entity.TradeStatus;
import com.example.bakend.trade.repository.TradeRepository;
import com.example.bakend.user.entity.Role;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public User verifyUser(Long userId, boolean verified) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setVerified(verified);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Autowired
    private SchemeRepository schemeRepository;

    public Scheme verifyScheme(Long schemeId, boolean verified) {

        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new RuntimeException("Scheme not found"));

        scheme.setVerifiedByAdmin(verified);
        return schemeRepository.save(scheme);
    }




    public AdminDashboardResponse getDashboardMetrics() {

        return AdminDashboardResponse.builder()
                .totalUsers(userRepository.count())
                .verifiedUsers(userRepository.countByVerifiedTrue())
                .farmers(userRepository.countByRole(Role.ROLE_FARMER))
                .traders(userRepository.countByRole(Role.ROLE_TRADER))
                .totalTrades(tradeRepository.count())
                .soldTrades(tradeRepository.countByStatus(TradeStatus.SOLD))
                .totalPayments(paymentRepository.count())
                .successfulPayments(
                        paymentRepository.countByStatus(
                                com.example.bakend.payment.entity.PaymentStatus.SUCCESS
                        )
                )
                .build();
    }


    public User blockOrUnblockUser(Long userId, boolean active) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() == Role.ROLE_ADMIN && !active) {
            throw new RuntimeException("Admin cannot be blocked");
        }

        user.setActive(active);
        return userRepository.save(user);
    }

}
