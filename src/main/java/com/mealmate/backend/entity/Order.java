package com.mealmate.backend.entity;


import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Consumer consumer;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}

