package com.batrawy.LibraryManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
