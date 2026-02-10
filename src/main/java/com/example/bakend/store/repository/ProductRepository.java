package com.example.bakend.store.repository;


import com.example.bakend.store.dto.SellerType;
import com.example.bakend.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    List<Product> findBySellerTypeAndActiveTrue(SellerType sellerType);
}
