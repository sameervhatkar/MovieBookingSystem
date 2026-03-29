package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class ShowSeatResponseDTO {
    private Long id;
    private Long showId;
    private Long seatId;
    private ShowSeatStatus status;
    private Long lockedByUserId;
    private LocalDateTime lockExpiryTime;
    private Instant createdAt;
    private Instant updatedAt;
}
