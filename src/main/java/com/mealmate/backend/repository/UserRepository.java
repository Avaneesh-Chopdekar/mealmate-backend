package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Role;
import com.mealmate.backend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findByRole(Role role, Pageable pageable);

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmail(String email);
}
