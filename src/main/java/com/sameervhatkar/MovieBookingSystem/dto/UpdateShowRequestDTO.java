package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateShowRequestDTO {
    @NotNull
    private Long id;
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
