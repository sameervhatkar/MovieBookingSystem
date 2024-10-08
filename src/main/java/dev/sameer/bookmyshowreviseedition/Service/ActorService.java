package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Actor;

public interface ActorService {
    Actor createActor(String actorName);
    Actor findActor(String actorName);
}
