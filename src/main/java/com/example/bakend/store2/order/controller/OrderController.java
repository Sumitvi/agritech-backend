package com.example.bakend.store2.order.controller;

import com.example.bakend.store2.order.dto.*;
import com.example.bakend.store2.order.entity.Order;
import com.example.bakend.store2.order.service.OrderService;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/place")
    public OrderResponse placeOrder(@RequestBody PlaceOrderRequest request) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User farmer = userRepository.findByMobile(username)
                .orElseThrow();

        Order order = orderService.placeOrder(farmer);

        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus().name())
                .totalAmount(order.getTotalAmount())
                .items(order.getItems().stream()
                        .map(i -> OrderItemResponse.builder()
                                .productId(i.getProduct().getId())
                                .productName(i.getProduct().getName())
                                .quantity(i.getQuantity())
                                .price(i.getPrice())
                                .build())
                        .toList())
                .build();
    }





    // 🚚 SHIP ORDER (Store Owner / Admin)
    @PostMapping("/{orderId}/ship")
    public OrderResponse shipOrder(@PathVariable Long orderId) {

        Order order = orderService.markAsShipped(orderId);
        return map(order);
    }

    // 📦 DELIVER ORDER (Store Owner / Admin)
    @PostMapping("/{orderId}/deliver")
    public OrderResponse deliverOrder(@PathVariable Long orderId) {

        Order order = orderService.markAsDelivered(orderId);
        return map(order);
    }

    // 👨‍🌾 FARMER – MY ORDERS
    @GetMapping("/my")
    public List<OrderResponse> myOrders() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User farmer = userRepository.findByMobile(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderService.getOrdersForFarmer(farmer)
                .stream()
                .map(this::map)
                .toList();
    }

    // 🔁 Mapper
    private OrderResponse map(Order order) {

        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus().name())
                .totalAmount(order.getTotalAmount())
                .items(order.getItems().stream()
                        .map(i -> OrderItemResponse.builder()
                                .productId(i.getProduct().getId())
                                .productName(i.getProduct().getName())
                                .quantity(i.getQuantity())
                                .price(i.getPrice())
                                .build())
                        .toList())
                .build();
    }
}
