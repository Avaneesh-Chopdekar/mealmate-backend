package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Consumer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource
@Tag(name = "Consumer API")
public interface ConsumerRepository extends BaseRepository<Consumer, UUID> {

    @Query("SELECT c FROM Consumer c LEFT JOIN FETCH c.addresses WHERE c.id = :id")
    Optional<Consumer> findByIdWithAddresses(@Param("id") UUID id);

    Page<Consumer> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}