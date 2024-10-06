package dev.sameer.bookmyshowreviseedition.DTOs;

import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Enum.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TicketResponseDTO {
    private double ticketPrice;
    private List<Seat> bookedSeats;
    private String movieName;
    private LocalDateTime showTiming;
    private LocalDateTime bookingTime;
    private TicketStatus status;
}
