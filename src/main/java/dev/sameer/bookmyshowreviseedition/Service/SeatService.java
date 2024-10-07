package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;
import dev.sameer.bookmyshowreviseedition.Entity.Seat;

import java.util.UUID;

public interface SeatService {
    Seat createSeat();
    void saveSeat(Seat seat);
    Seat getSeat(UUID seatId);
    void deleteSeat(UUID seatId);
}
