package com.mealmate.backend.repository.event_handler;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@RepositoryEventHandler(Order.class)
public class OrderEventHandler {

    private OrderRepository orderRepository;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void calculateTotalPrice(Order order) {
        double totalPrice = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);
    }
}
