package com.example.bakend.store2.cart.controller;

import com.example.bakend.store2.cart.dto.*;
import com.example.bakend.store2.cart.entity.Cart;
import com.example.bakend.store2.cart.service.CartService;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody AddToCartRequest request) {
        User farmer = getCurrentUser();
        return map(cartService.addToCart(
                farmer, request.getProductId(), request.getQuantity()));
    }

    @DeleteMapping("/remove")
    public CartResponse remove(@RequestBody RemoveCartItemRequest request) {
        User farmer = getCurrentUser();
        return map(cartService.removeItem(farmer, request.getProductId()));
    }

    @PutMapping("/update")
    public CartResponse update(@RequestBody UpdateCartItemRequest request) {
        User farmer = getCurrentUser();
        return map(cartService.updateQuantity(
                farmer, request.getProductId(), request.getQuantity()));
    }

    @GetMapping
    public CartResponse viewCart() {
        return map(cartService.getCart(getCurrentUser()));
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepository.findByMobile(username)
                .orElseThrow();
    }

    private CartResponse map(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(i -> CartItemResponse.builder()
                        .productId(i.getProduct().getId())
                        .productName(i.getProduct().getName())
                        .quantity(i.getQuantity())
                        .price(i.getProduct().getPrice())
                        .build())
                .toList();

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalAmount(total)
                .build();
    }
}
