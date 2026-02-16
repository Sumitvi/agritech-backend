package com.example.bakend.store.controller;

import com.example.bakend.store.dto.AddProductRequest;
import com.example.bakend.store.entity.Product;
import com.example.bakend.store.service.ProductService;
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

    /**
     * Store Owner adds a product
     * ROLE_STORE_OWNER
     */
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(
            @RequestBody AddProductRequest request) {

        // 🔐 Get logged-in store owner from JWT
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        // username = mobile/email (based on your JWT)

        // TEMP approach (recommended for now):
        // pass storeOwnerId manually after fetching user
        // In next step we’ll centralize this in a helper

        Long storeOwnerId = Long.parseLong(username);
        // ⚠️ Use only if JWT 'sub' = userId
        // Otherwise replace with userRepository lookup

        return ResponseEntity.ok(
                productService.addProductByStoreOwner(request, storeOwnerId)
        );
    }

    /**
     * Farmer views all active products
     * ROLE_FARMER
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> viewProducts() {
        return ResponseEntity.ok(
                productService.getAllActiveProducts()
        );
    }





    @PostMapping("/deactivate/{productId}")
    public ResponseEntity<Map<String, String>> deactivateProduct(
            @PathVariable Long productId) {

        productService.deactivateProduct(productId);

        return ResponseEntity.ok(
                Map.of("message", "Product deactivated")
        );
    }
}
