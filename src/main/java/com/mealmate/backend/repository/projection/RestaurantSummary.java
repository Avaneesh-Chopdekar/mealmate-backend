package com.mealmate.backend.repository.projection;

import com.mealmate.backend.entity.Restaurant;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "restaurantSummary", types = { Restaurant.class })
public interface RestaurantSummary {
    String getName();
    String getLocation();
}