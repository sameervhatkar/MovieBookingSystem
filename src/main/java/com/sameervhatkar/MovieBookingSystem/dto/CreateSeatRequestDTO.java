package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSeatRequestDTO {
    @NotNull
    private Long auditoriumId;
    @NotBlank
    private String row_label;
    @NotNull
    private Integer seat_number;
    @NotNull
    private SeatType seatType;
}
