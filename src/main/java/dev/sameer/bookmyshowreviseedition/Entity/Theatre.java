package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String theatreName;
    private String theatreAddress;
    @OneToMany
    private List<Auditorium> auditoriumList;
    @ManyToMany
    private List<Movie> movieList;
}
