package com.mealmate.backend.repository;

import com.mealmate.backend.entity.Rider;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
@RepositoryRestResource
@Tag(name = "Rider API")
public interface RiderRepository extends BaseRepository<Rider, UUID> {

    // Find riders by vehicle type (e.g., "Bike", "Car")
    List<Rider> findByVehicleType(@Param("vehicleType") String vehicleType);

    @Query("SELECT r FROM Rider r JOIN r.deliveries d WHERE d.status = 'PENDING'")
    List<Rider> findRidersWithPendingDeliveries();
}
