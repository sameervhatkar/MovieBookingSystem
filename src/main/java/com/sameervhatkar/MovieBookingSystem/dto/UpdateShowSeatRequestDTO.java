package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.entity.ShowSeat;
import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateShowSeatRequestDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long showId;
    @NotNull
    private Long seatId;
    @NotNull
    private ShowSeatStatus status;
}
