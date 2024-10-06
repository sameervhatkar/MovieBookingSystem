package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShowSeatRepo extends JpaRepository<ShowSeat, UUID> {

}
