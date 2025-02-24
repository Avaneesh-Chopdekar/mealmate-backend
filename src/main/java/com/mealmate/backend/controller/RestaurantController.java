package com.mealmate.backend.controller;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import com.mealmate.backend.repository.OrderRepository;
import com.mealmate.backend.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Restaurant API")
@RequiredArgsConstructor
@RepositoryRestController
public class RestaurantController {

    private final OrderRepository orderRepository;

    @PutMapping("/restaurants/{orderId}/accept")
    public ResponseEntity<Order> acceptOrder(@PathVariable UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(OrderStatus.ACCEPTED);
        return ResponseEntity.ok(orderRepository.save(order));
    }
}

