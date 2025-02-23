package com.mealmate.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends User {

    @Column(nullable = false)
    private String department = "General";

    @Column(nullable = false)
    private boolean superAdmin = false;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SystemLog> systemLogs = new ArrayList<>();
}
