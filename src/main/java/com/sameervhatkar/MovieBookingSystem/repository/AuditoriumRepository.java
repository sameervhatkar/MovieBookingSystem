package com.sameervhatkar.MovieBookingSystem.repository;

import com.sameervhatkar.MovieBookingSystem.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
