package com.sameervhatkar.MovieBookingSystem.repository;

import com.sameervhatkar.MovieBookingSystem.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
