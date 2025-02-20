package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {}