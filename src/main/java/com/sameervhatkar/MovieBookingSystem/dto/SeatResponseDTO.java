package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.SeatType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SeatResponseDTO {
    private Long id;
    private Long auditoriumId;
    private String row_label;
    private Integer seat_number;
    private SeatType seatType;
    private Instant createdAt;
    private Instant updatedAt;
}
