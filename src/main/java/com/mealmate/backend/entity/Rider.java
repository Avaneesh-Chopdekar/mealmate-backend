package com.mealmate.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mealmate.backend.utils.HelperSingletonService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "riders")
public class Rider extends User {

    @NotBlank
    private String vehicleType = "Bike"; // Example: "Bike", "Car"

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> deliveries = new ArrayList<>();

    @Column(nullable = false)
    private boolean available = true;

    @Column
    private String vehicleNumber;

    @JsonIgnore
    @Column
    private String cloudinaryPublicId;

    @JsonIgnore
    @Column(name = "image_url")
    private String imageUrl;

    @JsonProperty
    public String avatar() {
        return Objects.requireNonNullElse(
                HelperSingletonService
                        .getImageUploadService()
                        .generateTransformedImageUrl(
                                cloudinaryPublicId, 400, 400
                        ),
                "https://placehold.co/150x150?text=NO+IMAGE"
        );
    }
}