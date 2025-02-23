package com.mealmate.backend.repository;

import com.mealmate.backend.entity.MenuItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface MenuItemRepository extends BaseRepository<MenuItem, UUID> {

    List<MenuItem> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    List<MenuItem> findByPriceBetween(@Param("min") Double min, @Param("max") Double max, Pageable pageable);

    List<MenuItem> findByRestaurantId(@Param("restaurantId") UUID restaurantId, Pageable pageable);
}