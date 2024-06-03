package com.batrawy.LibraryManagementSystem.repository;

import com.batrawy.LibraryManagementSystem.model.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}