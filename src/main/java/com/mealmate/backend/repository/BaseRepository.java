package com.mealmate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

// TODO: Later create custom delete and soft delete endpoints
//        for each entity in their respective controllers

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    @Override
    @RestResource(exported = false) // Disable DELETE via REST
    void deleteById(ID id);

    @Override
    @RestResource(exported = false)
    void delete(T entity);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = :id")
    void softDeleteById(@Param("id") ID id);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id IN :ids")
    void softDeleteByIds(@Param("ids") List<ID> ids);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    List<T> findAllActive();
}
