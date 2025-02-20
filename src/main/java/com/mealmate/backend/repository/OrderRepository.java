package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Order;
import com.mealmate.backend.entity.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends BaseRepository<Order, Long> {

    List<Order> findByStatus(@Param("status") OrderStatus status, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.consumer.id = :consumerId")
    List<Order> findByConsumerIdWithItems(@Param("consumerId") Long consumerId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.rider.id = :riderId")
    List<Order> findByRiderId(@Param("riderId") Long riderId, Pageable pageable);
}
