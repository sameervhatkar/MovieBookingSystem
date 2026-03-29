package com.sameervhatkar.MovieBookingSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class ShowResponseDTO {
    private Long id;
    private Long movieId;
    private Long auditoriumId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double basePrice;
    private Instant createdAt;
    private Instant updatedAt;
}
