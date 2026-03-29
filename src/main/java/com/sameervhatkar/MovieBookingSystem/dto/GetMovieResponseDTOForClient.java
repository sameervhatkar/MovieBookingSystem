package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetMovieResponseDTOForClient {
    private String title;
    private Integer durationInMinutes;
    private Genre genre;
    private String originalLanguage;
    private LocalDate releaseDate;
}
