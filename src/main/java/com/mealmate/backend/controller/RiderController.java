package com.mealmate.backend.controller;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import com.mealmate.backend.repository.OrderRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Rider API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
public class RiderController {

    private final OrderRepository orderRepository;

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> markAsDelivered(@PathVariable UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.DELIVERED);
        return ResponseEntity.ok(orderRepository.save(order));
    }
}
