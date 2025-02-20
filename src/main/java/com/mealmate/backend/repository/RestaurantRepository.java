package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Restaurant;
import com.mealmate.backend.repository.projection.RestaurantSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(excerptProjection = RestaurantSummary.class)
public interface RestaurantRepository extends BaseRepository<Restaurant, Long> {

    List<Restaurant> findByLocationContainingIgnoreCase(@Param("location") String location, Pageable pageable);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menuItems WHERE r.id = :id")
    Optional<Restaurant> findByIdWithMenu(@Param("id") Long id);
}
