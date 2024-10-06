package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatRepo extends JpaRepository<Seat, UUID> {
}
