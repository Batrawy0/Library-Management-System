package com.batrawy.LibraryManagementSystem.repository;

import com.batrawy.LibraryManagementSystem.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}