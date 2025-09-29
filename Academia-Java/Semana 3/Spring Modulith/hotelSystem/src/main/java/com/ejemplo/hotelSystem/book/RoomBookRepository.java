package com.ejemplo.hotelSystem.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomBookRepository extends JpaRepository<RoomEntry,Long> {
    Optional<RoomEntry> findByNameAndActiveTrue(String name);
}