package com.mealmate.backend.repository;

import com.mealmate.backend.entity.OrderItem;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RepositoryRestResource
@Tag(name = "Order Item API")
public interface OrderItemRepository extends BaseRepository<OrderItem, UUID> {

    List<OrderItem> findByOrderId(@Param("orderId") UUID orderId, Pageable pageable);
}
