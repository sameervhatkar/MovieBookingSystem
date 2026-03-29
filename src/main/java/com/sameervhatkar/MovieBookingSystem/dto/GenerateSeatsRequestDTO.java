package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.SeatType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateSeatsRequestDTO {

    @NotNull
    private Long auditoriumId;

    @NotNull
    private List<String> rows;

    @NotNull
    private Integer seatsPerRow;

    @NotNull
    private SeatType seatType;
}