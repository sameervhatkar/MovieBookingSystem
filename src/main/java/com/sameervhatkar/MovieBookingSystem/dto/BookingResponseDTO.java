package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class BookingResponseDTO {

    private Long id;
    private Long userId;
    private Long showId;
    private BookingStatus status;
    private Double totalAmount;
    private Instant createdAt;
    private Instant updatedAt;
}