package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepo extends JpaRepository<Movie, UUID> {
    Optional<Movie> findMovieByMovieNameIgnoreCase(String movieName);
}
