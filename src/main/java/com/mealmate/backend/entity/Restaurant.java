package com.mealmate.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RESTAURANT")
public class Restaurant extends User {
    private String location;
}