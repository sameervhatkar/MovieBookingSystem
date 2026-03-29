package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.ScreenType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class AudiResponseDTO {
    private Long id;
    private String name;
    private Long theatreId;
    private ScreenType screenType;
    private Instant createdAt;
    private Instant updatedAt;
}
