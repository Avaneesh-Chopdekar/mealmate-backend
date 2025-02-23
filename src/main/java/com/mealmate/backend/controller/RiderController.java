package com.mealmate.backend.controller;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import com.mealmate.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
public class RiderController {

    private OrderRepository orderRepository;

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> markAsDelivered(@PathVariable UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.DELIVERED);
        return ResponseEntity.ok(orderRepository.save(order));
    }
}
