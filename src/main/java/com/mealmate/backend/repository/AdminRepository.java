package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Admin;
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
@RepositoryRestResource
@Tag(name = "Admin API")
public interface AdminRepository extends BaseRepository<Admin, UUID> {

    Optional<Admin> findByEmail(String email);

    @Query("SELECT a FROM Admin a WHERE a.name LIKE %:name%")
    List<Admin> findByNameContaining(@Param("name") String name, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE a.department = :department")
    List<Admin> findByDepartment(@Param("department") String department, Pageable pageable);

    @Query("SELECT a FROM Admin a WHERE a.superAdmin = true")
    List<Admin> findSuperAdmins();
}

