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
    public Actor findOrCreateActor(String actorName) {
        return actorRepo.findByActorName(actorName).orElseGet(() -> {
            Actor newActor = new Actor(actorName);
            actorRepo.save(newActor);
            return newActor;
        });
    }
}
