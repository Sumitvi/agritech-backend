package com.example.bakend.store2.order.repository;
import com.example.bakend.store2.order.entity.Order;
import com.example.bakend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByFarmer(User farmer);
}
