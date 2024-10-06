package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class MovieResponseDTO {
    private String movieName;
    private Date releaseDate;
    private List<String> actorsName;
    private String directorName;
    private int movieDuration;
}
