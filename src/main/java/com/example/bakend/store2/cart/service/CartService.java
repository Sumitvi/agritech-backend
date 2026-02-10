package com.example.bakend.store2.cart.service;

import com.example.bakend.store.entity.Product;
import com.example.bakend.store.repository.ProductRepository;
import com.example.bakend.store2.cart.entity.Cart;
import com.example.bakend.store2.cart.entity.CartItem;
import com.example.bakend.store2.cart.repository.CartRepository;
import com.example.bakend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(User farmer, Long productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByFarmer(farmer)
                .orElse(Cart.builder()
                        .farmer(farmer)
                        .items(new ArrayList<>())
                        .build());

        CartItem item = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();

        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public Cart getCart(User farmer) {
        return cartRepository.findByFarmer(farmer)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));
    }

    public Cart removeItem(User farmer, Long productId) {
        Cart cart = getCart(farmer);
        cart.getItems().removeIf(
                i -> i.getProduct().getId().equals(productId)
        );
        return cartRepository.save(cart);
    }

    public Cart updateQuantity(User farmer, Long productId, int quantity) {
        Cart cart = getCart(farmer);
        cart.getItems().forEach(i -> {
            if (i.getProduct().getId().equals(productId)) {
                i.setQuantity(quantity);
            }
        });
        return cartRepository.save(cart);
    }

    public void clearCart(Cart cart) {
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
