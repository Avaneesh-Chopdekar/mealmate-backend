package com.mealmate.backend.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONSUMER")
public class Consumer extends User {
    private String address;
}