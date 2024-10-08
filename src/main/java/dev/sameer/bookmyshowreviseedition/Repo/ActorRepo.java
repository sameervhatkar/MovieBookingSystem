package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActorRepo extends JpaRepository<Actor, UUID> {
    Optional<Actor> findByActorName(String actorName);
}
