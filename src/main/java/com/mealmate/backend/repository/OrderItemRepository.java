package com.mealmate.backend.repository;

import com.mealmate.backend.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(@Param("orderId") Long orderId, Pageable pageable);
}
