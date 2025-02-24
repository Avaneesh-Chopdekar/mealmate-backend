package com.mealmate.backend.controller;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import com.mealmate.backend.repository.OrderRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Order API")
@RequiredArgsConstructor
@RepositoryRestController
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        order.setStatus(OrderStatus.PENDING);
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PatchMapping("/orders/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable UUID id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(OrderStatus.CANCELLED);
                    orderRepository.save(order);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
