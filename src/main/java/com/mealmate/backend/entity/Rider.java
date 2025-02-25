package com.mealmate.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "riders")
public class Rider extends User {

    @NotBlank
    private String vehicleType = "Bike"; // Example: "Bike", "Car"

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> deliveries = new ArrayList<>();

    @Column(name = "image_url")
    private String imageUrl;
}