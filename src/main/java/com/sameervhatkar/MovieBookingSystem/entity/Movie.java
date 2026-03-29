package com.sameervhatkar.MovieBookingSystem.entity;

import com.sameervhatkar.MovieBookingSystem.enums.Genre;
import com.sameervhatkar.MovieBookingSystem.enums.MovieStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
public class Movie extends BaseModel {
    private String title;
    private Integer durationInMinutes;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String originalLanguage;
    private LocalDate releaseDate;
    private MovieStatus status;

}
