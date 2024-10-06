package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre, UUID> {

}
