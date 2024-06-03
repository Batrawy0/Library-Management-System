package com.batrawy.LibraryManagementSystem.repository;

import com.batrawy.LibraryManagementSystem.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}