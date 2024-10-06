package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MovieRequestDTO {
    private String movieName;
    private Date releaseDate;
    private List<String> actorsName;
    private String directorName;
    private int movieDuration;
}
