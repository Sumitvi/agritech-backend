package com.example.bakend.store2.cart.repository;
import com.example.bakend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bakend.store2.cart.entity.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByFarmer(User farmer);
}
