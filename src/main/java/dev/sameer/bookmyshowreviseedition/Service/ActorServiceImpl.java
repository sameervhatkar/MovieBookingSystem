package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Actor;
import dev.sameer.bookmyshowreviseedition.Exceptions.ActorNotFoundException;
import dev.sameer.bookmyshowreviseedition.Repo.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService{

    @Autowired
    private ActorRepo actorRepo;

    @Override
    public Actor createActor(String actorName) {
        Actor actor = new Actor(actorName);
        actorRepo.save(actor);
        return actor;
    }

    @Override
    public Actor findActor(String actorName) {
        return actorRepo.findByActorName(actorName).orElseThrow(
                () -> new ActorNotFoundException("Actor not found")
        );
    }
}
