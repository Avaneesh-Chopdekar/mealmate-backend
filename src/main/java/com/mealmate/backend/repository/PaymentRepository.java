package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Payment;
import com.mealmate.backend.entity.PaymentStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource
@Tag(name = "Payment API")
public interface PaymentRepository extends BaseRepository<Payment, UUID> {

    List<Payment> findByStatus(@Param("status") PaymentStatus status, Pageable pageable);

    Optional<Payment> findByOrderId(@Param("orderId") UUID orderId);
}