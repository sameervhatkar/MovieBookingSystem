package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowSeatRequestDTO {
    @NotNull
    private Long showId;

    @NotNull
    private Long seatId;
}
