package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String movieName;
    private Date releaseDate;
    private List<String> actorsName;
    private String directorName;
    private int movieDuration;
}
