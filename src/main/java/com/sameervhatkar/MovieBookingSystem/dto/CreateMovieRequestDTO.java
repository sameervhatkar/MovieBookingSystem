package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateMovieRequestDTO {
    @NotBlank
    private String title;
    @Positive
    @NotNull
    private Integer durationInMinutes;
    @NotNull
    private Genre genre;
    @NotBlank
    private String originalLanguage;
    @NotNull
    private LocalDate releaseDate;
}
