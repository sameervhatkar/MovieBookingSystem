package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.Genre;
import com.sameervhatkar.MovieBookingSystem.enums.MovieStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class CreateMovieResponseDTO {
    private Long id;
    private String title;
    private Integer durationInMinutes;
    private Genre genre;
    private String originalLanguage;
    private LocalDate releaseDate;
    private MovieStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
