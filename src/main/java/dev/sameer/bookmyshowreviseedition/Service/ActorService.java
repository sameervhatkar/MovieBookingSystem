package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Actor;

public interface ActorService {
    Actor findOrCreateActor(String actorName);
}
