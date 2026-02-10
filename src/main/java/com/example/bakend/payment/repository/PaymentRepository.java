package com.example.bakend.payment.repository;
import com.example.bakend.payment.entity.Payment;
import com.example.bakend.payment.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByPayerId(Long payerId);

    List<Payment> findByPayeeId(Long payeeId);

    long countByStatus(PaymentStatus status);

    List<Payment> findByStatus(PaymentStatus paymentStatus);
}
