package com.sameervhatkar.MovieBookingSystem.repository;

import com.sameervhatkar.MovieBookingSystem.entity.ShowSeat;
import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findByShowId(Long id);
    List<ShowSeat> findByStatusAndLockExpiryTimeBefore(ShowSeatStatus status, LocalDateTime localDateTime);
}
