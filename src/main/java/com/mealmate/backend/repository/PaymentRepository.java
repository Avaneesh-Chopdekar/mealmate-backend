package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Payment;
import com.mealmate.backend.entity.PaymentStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PaymentRepository extends BaseRepository<Payment, Long> {

    List<Payment> findByStatus(@Param("status") PaymentStatus status, Pageable pageable);

    Optional<Payment> findByOrderId(@Param("orderId") Long orderId);
}