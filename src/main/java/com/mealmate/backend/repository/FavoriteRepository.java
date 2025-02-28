package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Favorite;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RepositoryRestResource(collectionResourceRel = "favorites", path = "favorites")
public interface FavoriteRepository extends BaseRepository<Favorite, UUID> {
    List<Favorite> findByUserId(UUID userId);
}