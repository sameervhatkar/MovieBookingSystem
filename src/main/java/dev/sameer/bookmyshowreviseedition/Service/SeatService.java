package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;
import dev.sameer.bookmyshowreviseedition.Entity.Seat;

public interface SeatService {
    Seat createSeat();
    void saveSeat(Seat seat);
}
