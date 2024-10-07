package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {
    private String theatreName;
    private String audiName;
    private int capacity;
    @OneToMany
    private List<Seat> seatList;
    @OneToMany
    private List<Show> shows;
}
