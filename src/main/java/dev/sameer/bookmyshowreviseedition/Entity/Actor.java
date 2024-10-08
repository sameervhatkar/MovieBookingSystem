package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Actor extends BaseModel {
    private String actorName;
    @ManyToMany
    private List<Movie> moviesActedOn;

    public Actor() {
    }

    public Actor(String actorName) {
        this.actorName = actorName;
    }
}
