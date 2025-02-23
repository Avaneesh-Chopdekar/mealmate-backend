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
@RequestMapping("/restaurants")
public class RestaurantController {

    private OrderRepository orderRepository;

    @PutMapping("/{orderId}/accept")
    public ResponseEntity<Order> acceptOrder(@PathVariable UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.ACCEPTED);
        return ResponseEntity.ok(orderRepository.save(order));
    }
}

