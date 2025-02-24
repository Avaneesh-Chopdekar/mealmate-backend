package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Restaurant;
import com.mealmate.backend.repository.projection.RestaurantSummary;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource(excerptProjection = RestaurantSummary.class)
@Tag(name = "Restaurant API")
public interface RestaurantRepository extends BaseRepository<Restaurant, UUID> {

    List<Restaurant> findByLocationContainingIgnoreCase(@Param("location") String location, Pageable pageable);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuItems WHERE r.id = :id")
    Optional<Restaurant> findByIdWithMenu(@Param("id") UUID id);
}
