package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Consumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ConsumerRepository extends BaseRepository<Consumer, Long> {

    @Query("SELECT c FROM Consumer c LEFT JOIN FETCH c.addresses WHERE c.id = :id")
    Optional<Consumer> findByIdWithAddresses(@Param("id") Long id);

    Page<Consumer> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}