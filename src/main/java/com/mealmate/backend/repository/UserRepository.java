package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Role;
import com.mealmate.backend.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource
@Tag(name = "User API")
public interface UserRepository extends BaseRepository<User, UUID> {

    List<User> findByRole(Role role, Pageable pageable);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String email);
}
