package com.example.bakend.store.service;

import com.example.bakend.store.dto.AddProductRequest;
import com.example.bakend.store.dto.SellerType;
import com.example.bakend.store.entity.Product;
import com.example.bakend.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Store Owner adds a product
     */
    public Product addProductByStoreOwner(AddProductRequest request, Long storeOwnerId) {

        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .sellerType(SellerType.VENDOR)
                .sellerId(storeOwnerId)
                .active(true)
                .build();

        return productRepository.save(product);
    }

    /**
     * Farmer browses all active products
     */
    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    /**
     * Admin / Govt can deactivate a product (moderation)
     */
    public Product deactivateProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setActive(false);
        return productRepository.save(product);
    }
}
