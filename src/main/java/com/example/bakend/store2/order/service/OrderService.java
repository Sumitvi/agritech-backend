package com.example.bakend.store2.order.service;

import com.example.bakend.store.entity.Product;
import com.example.bakend.store.repository.ProductRepository;
import com.example.bakend.store2.cart.entity.Cart;
import com.example.bakend.store2.cart.service.CartService;
import com.example.bakend.store2.order.entity.*;
import com.example.bakend.store2.order.repository.OrderRepository;
import com.example.bakend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(User farmer) {

        Cart cart = cartService.getCart(farmer);

        var items = cart.getItems().stream().map(ci -> {
            Product product = ci.getProduct();

            if (product.getStockQuantity() < ci.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            product.setStockQuantity(
                    product.getStockQuantity() - ci.getQuantity());
            productRepository.save(product);

            return OrderItem.builder()
                    .product(product)
                    .quantity(ci.getQuantity())
                    .price(product.getPrice())
                    .build();

        }).collect(Collectors.toList());

        double total = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        Order order = Order.builder()
                .farmer(farmer)
                .items(items)
                .totalAmount(total)
                .status(OrderStatus.PLACED)
                .build();

        // 🔥 VERY IMPORTANT
        order = orderRepository.save(order);

        cartService.clearCart(cart);

        return order;
    }

    @Autowired
    private OrderRepository orderRepository;

    // 🚚 Update status → SHIPPED
    public Order markAsShipped(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new RuntimeException("Order cannot be shipped");
        }

        order.setStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }

    // 📦 Update status → DELIVERED
    public Order markAsDelivered(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Order cannot be delivered");
        }

        order.setStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    // 👨‍🌾 Farmer – My Orders
    public List<Order> getOrdersForFarmer(User farmer) {
        return orderRepository.findByFarmer(farmer);
    }
}
