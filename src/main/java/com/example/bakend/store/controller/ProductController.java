package com.example.bakend.store.controller;

import com.example.bakend.store.dto.AddProductRequest;
import com.example.bakend.store.entity.Product;
import com.example.bakend.store.service.ProductService;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/store")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    /**
     * STORE OWNER → Add product
     */
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(
            @RequestBody AddProductRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User storeOwner = userRepository.findByMobile(username)
                .orElseThrow(() -> new RuntimeException("Store owner not found"));

        return ResponseEntity.ok(
                productService.addProductByStoreOwner(request, storeOwner)
        );
    }

    /**
     * STORE OWNER → View my products
     */
    @GetMapping("/my-products")
    public ResponseEntity<List<Product>> getMyProducts() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User storeOwner = userRepository.findByMobile(username)
                .orElseThrow(() -> new RuntimeException("Store owner not found"));

        return ResponseEntity.ok(
                productService.getProductsByStoreOwner(storeOwner)
        );
    }

    /**
     * FARMER → View active products
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> viewProducts() {
        return ResponseEntity.ok(
                productService.getAllActiveProducts()
        );
    }

    /**
     * STORE OWNER → Deactivate product
     */
    @PostMapping("/deactivate/{productId}")
    public ResponseEntity<Map<String, String>> deactivateProduct(
            @PathVariable Long productId) {

        productService.deactivateProduct(productId);

        return ResponseEntity.ok(
                Map.of("message", "Product deactivated")
        );
    }
}
