package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface OrderRepository extends BaseRepository<Order, UUID> {

    List<Order> findByStatus(@Param("status") OrderStatus status, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN o.orderItems WHERE o.consumer.id = :consumerId")
    Page<Order> findByConsumerIdWithItems(@Param("consumerId") UUID consumerId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.rider.id = :riderId")
    Page<Order> findByRiderId(@Param("riderId") UUID riderId, Pageable pageable);
}
