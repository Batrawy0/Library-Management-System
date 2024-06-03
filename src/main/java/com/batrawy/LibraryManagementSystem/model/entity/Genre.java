package com.batrawy.LibraryManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
