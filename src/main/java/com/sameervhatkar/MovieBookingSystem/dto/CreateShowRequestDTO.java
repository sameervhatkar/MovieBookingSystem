package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateShowRequestDTO {
    @NotNull
    private Long movieId;
    @NotNull
    private Long auditoriumId;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private Double basePrice;
}
