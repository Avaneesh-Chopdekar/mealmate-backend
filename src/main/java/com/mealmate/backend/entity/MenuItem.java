package com.mealmate.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mealmate.backend.utils.HelperSingletonService;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "menu_items")
public class MenuItem extends BaseEntity {

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.1")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

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
